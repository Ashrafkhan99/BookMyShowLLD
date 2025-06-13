# BookMyShowLLD

# ER Diagram Analysis for BookMyShow Application

## Database Schema OverviewThe BookMyShow application follows a structured relational database design with a clear inheritance hierarchy. All entities extend from a `BaseModel` abstract class that provides common auditing fields (id, createdAt, lastModifiedAt), ensuring consistent tracking across all database tables.

## Entity Relationships Analysis### Core Entity GroupsThe application can be logically divided into several interconnected entity groups:

**User Management**: The `User` entity serves as the primary actor in the system, maintaining a one-to-many relationship with `Booking` entities to track all user reservations.

**Booking System**: The `Booking` entity acts as a central hub, connecting users to their reserved seats and payment information. Each booking maintains relationships with multiple `ShowSeat` entities (representing reserved seats) and `Payment` entities (for transaction tracking).

**Location Hierarchy**: The system follows a geographical hierarchy where `Region` contains multiple `Theatre` entities, and each `Theatre` houses multiple `Screen` entities. This structure supports multi-location operations.

**Seating Management**: The seating system is designed with flexibility in mind. Each `Screen` contains multiple `Seat` entities, where seats are categorized by `SeatType` (such as regular, premium, VIP). The `Seat` entity stores both seat identification (seatNumber) and positioning information (rowVal, colVal).

**Show Management**: `Movie` entities are scheduled as `Show` entities on specific `Screen` entities with defined start and end times. Each show can have various features (IMAX, 3D, etc.) to enhance the viewing experience.

### Junction EntitiesThe system employs two critical junction entities:

**ShowSeat**: This entity bridges the gap between shows, seats, and bookings. It tracks the availability status of each seat for each show and links to bookings when reserved.

**ShowSeatType**: This entity defines dynamic pricing by connecting shows with seat types, allowing different pricing strategies for different shows and seat categories.

## Visual RepresentationThe ER diagram illustrates the complete database schema with proper cardinality relationships. The entities are color-coded by functional groups for enhanced readability, with junction entities highlighted to show their bridging role in the system.
![Bookmyshow_clear_diagram](https://github.com/user-attachments/assets/469e3c80-4550-4af6-890f-430d635c0111)

## DEEP DIVE DIAGRAM:
![bookmyshow_er_diagram](https://github.com/user-attachments/assets/f897255f-3cb3-4279-bcb6-89c4bc85e6e7)

## Key Design PatternsThe schema demonstrates several important database design patterns:

**Inheritance Pattern**: All entities inherit from BaseModel, ensuring consistent auditing and primary key management across the system.

**Association Pattern**: Junction entities like ShowSeat and ShowSeatType handle many-to-many relationships while storing additional attributes specific to those associations.

**Hierarchical Pattern**: The Region → Theatre → Screen hierarchy provides geographical organization and supports scalable multi-location operations.

**Status Tracking**: Multiple enum-based status fields (BookingStatus, PaymentStatus, ShowSeatStatus) provide clear state management throughout the application lifecycle.

This well-designed schema supports all core BookMyShow functionalities including user registration, movie browsing, seat selection, booking management, and payment processing while maintaining data integrity and supporting future scalability requirements.
