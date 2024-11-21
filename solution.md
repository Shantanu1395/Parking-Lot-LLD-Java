### Class Relationship(Make entities and repositories)
1. ParkingLot {id, name, address} **–1:m–** Level {id, floorNumber, parkingLotId (FK)}
2. Level {id, floorNumber, parkingLotId (FK)} **–1:m–** ParkingSpot {id, spotSize, isOccupied, levelId (FK)}
3. ParkingSpot {id, spotSize, isOccupied, levelId (FK)} **–1:1–** Vehicle {id, licensePlate, type, parkedAt, parkingSpotId (FK)}
4. ParkingLot {id, name, address} **–1:m–** Gate {id, type, location, parkingLotId (FK)}
6. Vehicle {id, licensePlate, type} **–1:m–** Booking {id, startTime, endTime, parkingSpotId (FK), vehicleId (FK)}
7. ParkingSpot {id, spotSize, levelId} **–1:m–** ParkingRate {id, spotSize, hourlyRate, startTime, endTime}

#### Java mapping
```java
package com.example.parkinglot.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "parkingSpotId", nullable = false)
    private ParkingSpot parkingSpot;

    @ManyToOne
    @JoinColumn(name = "vehicleId", nullable = false)
    private Vehicle vehicle;

    // Getters and Setters
}
```

```java
package com.example.parkinglot.model;

import javax.persistence.*;

@Entity
public class Gate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String location;

    @ManyToOne
    @JoinColumn(name = "parkingLotId", nullable = false)
    private ParkingLot parkingLot;

    // Getters and Setters
}
```

```java
package com.example.parkinglot.model;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int floorNumber;

    @ManyToOne
    @JoinColumn(name = "parkingLotId", nullable = false)
    private ParkingLot parkingLot;

    @OneToMany(mappedBy = "level", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingSpot> parkingSpots;

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    // Getters and Setters
}
```

```java
package com.example.parkinglot.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Level> levels;

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gate> gates;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getters and Setters
}

```

```java
package com.example.parkinglot.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ParkingRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spotSize;
    private double hourlyRate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "parkingSpotId", nullable = false)
    private ParkingSpot parkingSpot;

    // Getters and Setters
}

```

```java
package com.example.parkinglot.model;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String spotSize;

    @Setter
    private boolean isOccupied;

    @Setter
    @ManyToOne
    @JoinColumn(name = "levelId", nullable = false)
    private Level level;

    @OneToOne(mappedBy = "parkingSpot", cascade = CascadeType.ALL, orphanRemoval = true)
    private Vehicle vehicle;

    @OneToMany(mappedBy = "parkingSpot", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParkingRate> parkingRates;

    public ParkingSpot(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public ParkingSpot() {
    }

    public void setIsOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setSpotSize(String spotSize) {
        this.spotSize = spotSize;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    // Getters and Setters
}

```

```java
package com.example.parkinglot.model;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;
    private String type;
    private LocalDateTime parkedAt;

    @OneToOne
    @JoinColumn(name = "parkingSpotId", nullable = false)
    private ParkingSpot parkingSpot;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    // Getters and Setters
}
```

### Make interfaces(this will be requirements)

```java
public interface ParkingLotService {
    ParkingLot createParkingLot(String name);
}
public interface GateService {
    Gate addGate(String type, String location, ParkingLot parkingLot);
}
public interface LevelService {
    Level addLevel(int floorNumber, ParkingLot parkingLot);
}
public interface VehicleService {
    Vehicle registerVehicle(String licensePlate, String type);
}
public interface ParkingSpotService {
    ParkingSpot createSpot(String spotSize, Long levelId);
    ParkingSpot assignSpotToVehicle(Vehicle vehicle);
    void releaseSpot(Long parkingSpotId);
}
public interface BookingService {
    Booking createBooking(Vehicle vehicle, Long parkingSpotId, LocalDateTime startTime, LocalDateTime endTime);
    Booking endBooking(Long bookingId);
    double calculateFee(Booking booking); // Simplified to exclude strategy as parameter
    Booking findById(Long bookingId);
}
Strategies

public interface FeeCalculationStrategy {
    double calculateFee(Booking booking);
}
public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(Vehicle vehicle);
}
Validations

public interface BookingValidator {
    void setNext(BookingValidator nextValidator);
    void validate(Booking request) throws ValidationException;
}
```

### Create Service implementations of interfaces
Services
```java
@Service
public class ParkingLotServiceImpl implements ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotServiceImpl(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Override
    public ParkingLot createParkingLot(String name) {
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.setName(name);
        return parkingLotRepository.save(parkingLot);
    }
}

@Service
public class GateServiceImpl implements GateService {

    private final GateRepository gateRepository;

    private GateServiceImpl(GateRepository gateRepository) {
        this.gateRepository = gateRepository;
    }

    // Factory Pattern for Creating Gate Objects
    @Override
    public Gate addGate(String type, String location, ParkingLot parkingLot) {
        Gate gate = Gate.createGate("Entrance", "Front", parkingLot);
        return gateRepository.save(gate);
    }
}
@Service
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level addLevel(int floorNumber, ParkingLot parkingLot) {
        Level level = Level.createLevel(floorNumber, parkingLot);
        return levelRepository.save(level);
    }
}
@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Override
    public Vehicle registerVehicle(String licensePlate, String vehicleType) {

        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate).orElse(Vehicle.create(licensePlate, vehicleType));
        return vehicleRepository.save(vehicle);
    }
}
@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private SpotAssignmentFactory spotAssignmentFactory;

    @Override
    public ParkingSpot createSpot(String spotSize, Long levelId) {
        Optional<Level> levelOptional = levelRepository.findById(levelId);
        if (levelOptional.isEmpty()) {
            throw new IllegalArgumentException("Level not found for ID: " + levelId);
        }

        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSpotSize(spotSize);
        parkingSpot.setIsOccupied(false);
        parkingSpot.setLevel(levelOptional.get());

        return parkingSpotRepository.save(parkingSpot);
    }

    @Override
    public ParkingSpot assignSpotToVehicle(Vehicle vehicle) {

        ParkingSpot assignedSpot = spotAssignmentFactory.ChooseStrategy(true).assignSpot(vehicle);

       if (assignedSpot == null) {
           throw new IllegalStateException("No available parking spot for vehicle type: " + vehicle.getType());
       }

       assignedSpot.setIsOccupied(true);
       assignedSpot.setVehicle(vehicle);
       assignedSpot.setState(SpotState.OCCUPIED);
       assignedSpot.setBaseParkingRate();
       vehicle.setParkingSpot(assignedSpot);

        parkingSpotRepository.save(assignedSpot);
        vehicleRepository.save(vehicle);

        return assignedSpot;
    }

    @Override
    public void releaseSpot(Long parkingSpotId) {

       Optional<ParkingSpot> parkingSpotOptional = parkingSpotRepository.findById(parkingSpotId);

        if (parkingSpotOptional.isEmpty()) {
            throw new IllegalArgumentException("Parking spot not found for ID: " + parkingSpotId);
        }

        ParkingSpot parkingSpot = parkingSpotOptional.get();
        if (parkingSpot.getState().equals(SpotState.AVAILABLE)) {
            throw new IllegalStateException("Parking spot is already vacant.");
        }

       Vehicle vehicle = parkingSpot.getVehicle();
       parkingSpot.setIsOccupied(false);
       parkingSpot.setVehicle(null);
       parkingSpot.setState(SpotState.AVAILABLE);

        if (vehicle != null) {
            vehicle.setParkingSpot(null);
            vehicleRepository.save(vehicle);
        }

        parkingSpotRepository.save(parkingSpot);
    }
}
@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Autowired
    private FeeCalculationFactory feeCalculationFactory;

    private BookingValidator validatorChain;

    @Override
    public Booking createBooking(Vehicle vehicle, Long parkingSpotId, LocalDateTime startTime, LocalDateTime endTime) {

        ParkingSpot parkingSpot = parkingSpotRepository.findById(parkingSpotId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid parking spot ID"));

        Booking booking = new Booking();
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setParkingSpot(parkingSpot);
        parkingSpot.setIsOccupied(true);
        booking.setState(BookingState.ACTIVE);
        booking.setVehicle(vehicle);
        parkingSpotRepository.save(parkingSpot);

        try {
            validatorChain =  BookingValidationChain.createChain(parkingLotService);
            validatorChain.validate(booking);
        } catch (ValidationException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
        return bookingRepository.save(booking);
    }

    @Override
    public Booking endBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));

        booking.setState(BookingState.COMPLETED);
        parkingSpotService.releaseSpot(booking.getParkingSpot().getSpotId());
        return bookingRepository.save(booking);
    }

    @Override
    public double calculateFee(Booking booking) {
        return feeCalculationFactory.ChooseStrategy(booking).calculateFee(booking);
    }

    public Booking findById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + bookingId));
    }
}
Concrete Strategies

Spot Assignment

@Component
public class SpotAssignmentFactory {

    @Autowired
    private  ExactSizeMatchStrategy exactSizeMatchStrategy;

    @Autowired
    private  FlexibleSizeMatchStrategy flexibleSizeMatchStrategy;

    public SpotAssignmentStrategy ChooseStrategy(boolean exact){
        if (exact){
            return exactSizeMatchStrategy;
        }else{
            return flexibleSizeMatchStrategy;
        }
    }
}
@Component
public class FlexibleSizeMatchStrategy implements SpotAssignmentStrategy {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Override
    public ParkingSpot assignSpot(Vehicle vehicle) {
        // Find a spot of the same size or larger
        Optional<ParkingSpot> spot = parkingSpotRepository.findAvailableFlexibleSpots(vehicle.getType())
                .stream()
                .findFirst();

        return spot.orElse(null);
    }
}
@Component
@Primary
public class ExactSizeMatchStrategy implements SpotAssignmentStrategy {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Override
    public ParkingSpot assignSpot(Vehicle vehicle) {
        Optional<ParkingSpot> spot = parkingSpotRepository.findAvailableSpotsBySize(vehicle.getType())
                .stream()
                .findFirst();

        return spot.orElse(null);
    }
}
Fees

@Component
public class FeeCalculationFactory {

    @Autowired
    private StandardFeeCalculationStrategy standardFeeCalculationStrategy;

    @Autowired
    private DiscountedFeeCalculationStrategy discountedFeeCalculationStrategy;

    public FeeCalculationStrategy ChooseStrategy(Booking booking){
        if (booking.getParkingSpot().getVehicle().getType().equals("Small")){
            return standardFeeCalculationStrategy;
        }else{
            return discountedFeeCalculationStrategy;
        }
    }
}
@Component
public class DiscountedFeeCalculationStrategy implements FeeCalculationStrategy {

    private final double discountPercentage;

    public DiscountedFeeCalculationStrategy(@Value("${fee.discount.percentage}") double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculateFee(Booking booking) {
        double hourlyRate = booking.getParkingSpot().getBaseParkingRate();
        double originalFee = hourlyRate * (booking.calculateDurationInHours() + 1);
        return originalFee - (originalFee * discountPercentage / 100);
    }
}
@Component
public class StandardFeeCalculationStrategy implements FeeCalculationStrategy {
    @Override
    public double calculateFee(Booking booking) {

        double hourlyRate = booking.getParkingSpot().getBaseParkingRate();
        return hourlyRate * (booking.calculateDurationInHours() + 1);
    }
}
Validations

public abstract class AbstractBookingValidator implements BookingValidator {
    protected BookingValidator nextValidator;

    @Override
    public void setNext(BookingValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(Booking request) throws ValidationException {
        if (nextValidator != null) {
            nextValidator.validate(request);
        }
    }
}
@Component
public class BookingValidationChain {
    public static BookingValidator createChain(ParkingLotService parkingLotService) {
        BookingValidator authValidator = new AuthenticationValidator();
        BookingValidator businessHoursValidator = new BusinessHoursValidator(
                LocalTime.of(6, 0), LocalTime.of(22, 0));
        BookingValidator spotAvailabilityValidator = new SpotAvailabilityValidator(parkingLotService);
        BookingValidator authenticationValidator = new AuthenticationValidator();
        // Add more validators as needed

        // Chain the validators
        authValidator.setNext(businessHoursValidator);
        businessHoursValidator.setNext(spotAvailabilityValidator);

        return authValidator; // Head of the chain
    }
}
public class BusinessHoursValidator extends AbstractBookingValidator {
    private LocalTime openingTime;
    private LocalTime closingTime;

    public BusinessHoursValidator(LocalTime openingTime, LocalTime closingTime) {
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    @Override
    public void validate(Booking request) throws ValidationException {
        LocalTime bookingTime = request.getStartTime().toLocalTime();

        if (bookingTime.isBefore(openingTime) || bookingTime.isAfter(closingTime)) {
            throw new ValidationException("Booking time is outside of business hours.");
        }
        super.validate(request);
    }
}
public class SpotAvailabilityValidator extends AbstractBookingValidator {
    private ParkingLotService parkingLotService;

    public SpotAvailabilityValidator(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public void validate(Booking booking) throws ValidationException {

        if (booking.getState().equals(BookingState.COMPLETED)) {
            throw new ValidationException("Parking spot is not available.");
        }
        super.validate(booking);
    }
}

Data Layer
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

    @Query("SELECT p FROM ParkingSpot p WHERE (p.spotSize = :spotSize OR p.spotSize > :spotSize) AND p.isOccupied = false")
    List<ParkingSpot> findAvailableFlexibleSpots(@Param("spotSize") String spotSize);

    @Query("SELECT p FROM ParkingSpot p WHERE p.spotSize = :spotSize AND p.isOccupied = false ")
    List<ParkingSpot> findAvailableSpotsBySize(@Param("spotSize") String spotSize);

    List<ParkingSpot> findAllByIsOccupiedFalse();

}
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    // Add custom queries if needed
    Optional<Vehicle> findByLicensePlate(String licensePlate);
}
```
