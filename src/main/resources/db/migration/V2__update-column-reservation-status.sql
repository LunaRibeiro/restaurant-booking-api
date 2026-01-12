select conname
from pg_constraint
where conrelid = 'reservations'::regclass
  and contype = 'c';

alter table reservations
    drop constraint reservations_reservation_status_check;

alter table reservations
    drop constraint reservations_reservation_status_check
        check (reservation_status IN (
                                      'PENDING',
                                      'CONFIRMED',
                                      'ACTIVE',
                                      'CANCELED'
            ));