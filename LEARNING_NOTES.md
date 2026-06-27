# Day 1

## @MappedSuperclass

- Used when a parent class should not have its own table.
- Child entities inherit all fields.
- Hibernate copies the fields into child tables.
- BaseEntity should be abstract because it is never instantiated.

---

## UUID

- Used as primary key.
- Generated automatically by Hibernate.
- More secure than sequential IDs.
- Public business IDs may be added later.

---

## Entity Listener

- Listens to entity lifecycle events.
- Used for auditing.
- Automatically sets createdAt and updatedAt.