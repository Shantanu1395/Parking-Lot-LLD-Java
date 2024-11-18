### Class Relationship
1. ParkingLot {id, name, address} **–1:m–** Level {id, floorNumber, parkingLotId (FK)}
2. Level {id, floorNumber, parkingLotId (FK)} **–1:m–** ParkingSpot {id, spotSize, isOccupied, levelId (FK)}
3. ParkingSpot {id, spotSize, isOccupied, levelId (FK)} **–1:1–** Vehicle {id, licensePlate, type, parkedAt, parkingSpotId (FK)}
4. ParkingLot {id, name, address} **–1:m–** Gate {id, type, location, parkingLotId (FK)}
6. Vehicle {id, licensePlate, type} **–1:m–** Booking {id, startTime, endTime, parkingSpotId (FK), vehicleId (FK)}
7. ParkingSpot {id, spotSize, levelId} **–1:m–** ParkingRate {id, spotSize, hourlyRate, startTime, endTime}

Make entities and repositories