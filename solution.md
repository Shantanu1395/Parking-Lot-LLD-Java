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
    ParkingLot createParkingLot(String name, String address); // Factory Pattern can be used in the implementation
    List<ParkingLot> getAllParkingLots();
    ParkingLot getParkingLotById(Long id);
    void deleteParkingLot(Long id);
}
```

```java
public interface LevelService {
    Level addLevel(int floorNumber, Long parkingLotId); // Factory Method Pattern for creating Levels
    List<Level> getLevelsByParkingLot(Long parkingLotId);
    void removeLevel(Long levelId);
}
```

```java
public interface ParkingSpotService {
    ParkingSpot createSpot(String spotSize, Long levelId); // Factory Pattern for creating spots
    ParkingSpot assignSpotToVehicle(Long parkingSpotId, Long vehicleId); // Strategy Pattern for spot assignment
    void releaseSpot(Long parkingSpotId);
    List<ParkingSpot> getAvailableSpots(String spotSize, Long levelId);
}
```

```java
public interface GateService {
    Gate addGate(String type, String location, Long parkingLotId); // Factory Pattern for creating gates
    List<Gate> getGatesByParkingLot(Long parkingLotId);
    void removeGate(Long gateId);
}
```

```java
public interface ParkingRateService {
    ParkingRate setRate(Long parkingSpotId, String spotSize, double hourlyRate, LocalDateTime startTime, LocalDateTime endTime);
    List<ParkingRate> getRatesBySpot(Long parkingSpotId);
    double calculateRate(Long parkingSpotId, LocalDateTime startTime, LocalDateTime endTime); // Template Method Pattern
}
```

```java
public interface VehicleService {
    Vehicle registerVehicle(String licensePlate, String type); // Factory Pattern for vehicle creation
    Vehicle getVehicleById(Long vehicleId);
    List<Vehicle> getVehiclesByUser(Long userId);
}
```

```java
public interface BookingService {
    Booking createBooking(Long vehicleId, Long parkingSpotId, LocalDateTime startTime, LocalDateTime endTime);
    Booking extendBooking(Long bookingId, LocalDateTime newEndTime);
    double calculateFee(Long bookingId); // Template Method Pattern for fee calculation
    List<Booking> getBookingsByVehicle(Long vehicleId);
}
```

### Create Service implementations of interfaces