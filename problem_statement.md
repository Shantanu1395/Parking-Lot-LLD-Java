# Problem Statement: Design a Parking Lot System

You are tasked with designing a **Parking Lot System** for a multi-level parking structure. The system should manage parking spots, vehicles, and payments efficiently while adhering to object-oriented design principles. The solution should focus on **scalability**, **extensibility**, and **maintainability**.

---

## Requirements

### 1. Parking Lot Layout
- The parking lot can have multiple levels.
- Each level can have multiple rows of parking spots.
- Parking spots can be of different sizes:
    - **Small (S)**: For bikes and compact cars.
    - **Medium (M)**: For sedans and mid-sized vehicles.
    - **Large (L)**: For trucks, buses, and larger vehicles.
- Each parking spot is uniquely identified by a spot ID.

### 2. Vehicle Types
- The system should support different types of vehicles:
    - **Bike**
    - **Car**
    - **Truck**
- Each vehicle should occupy a spot of the appropriate size. Larger spots can accommodate smaller vehicles but not vice versa.

### 3. Parking Rules
- Vehicles should be parked in the nearest available spot (based on entry point).
- Once a spot is occupied, it is marked as unavailable.
- When a vehicle leaves, the spot becomes available for other vehicles.

### 4. User Features
Users should be able to:
- Find and book an available parking spot.
- Extend their parking time.
- Make payments for their parking duration.
- View parking history (e.g., entry and exit times, total cost).

### 5. System Features
The system should:
- Handle multiple entry and exit points.
- Support real-time tracking of parking spot availability.
- Calculate parking charges based on:
    - **Duration** (e.g., hourly rates).
    - **Spot size** (e.g., higher charges for larger spots).
- Generate a receipt upon exit.

### 6. Admin Features
Admins should be able to:
- Add/remove parking levels and spots.
- Monitor real-time occupancy of the parking lot.
- View revenue reports.

---

## Assumptions
- Payments can be made using cash, card, or mobile payments.
- Entry and exit gates can scan vehicle registration numbers.
- There is a fixed hourly rate for each spot type:
    - **Small**: $10/hour
    - **Medium**: $15/hour
    - **Large**: $20/hour

---

## Constraints
- The system should be scalable to handle a parking lot with hundreds of levels and thousands of spots.
- It should be able to handle high traffic during peak hours.
- Fault tolerance should be ensured for critical operations like payments and spot allocation.

---

## Goals
1. **Object-Oriented Design Principles**:
    - Encapsulation, abstraction, and inheritance should be utilized effectively.
2. **Extensibility**:
    - Adding new features like loyalty programs, automated valet parking, or EV charging stations should require minimal changes.
3. **Efficiency**:
    - Minimize the time taken for spot allocation, payment processing, and updates to availability.

---

### Sample Questions to Clarify
1. Do different entry points have different parking capacities or rules?
2. Are there time-based discounts (e.g., early bird, overnight)?
3. Should the system support pre-booking of parking spots?
4. Do you want to simulate a real-time parking lot system for testing?