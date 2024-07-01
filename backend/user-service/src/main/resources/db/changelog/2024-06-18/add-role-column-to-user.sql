alter table users
    add column if not exists role varchar(255) check ( role in ('DRIVER', 'MANAGER'));