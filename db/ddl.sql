
CREATE TABLE reservation.roles
(
    id                          MEDIUMINT NOT NULL,
    name                        VARCHAR(30),
    role                        VARCHAR(30) NOT NULL,
    created_user_id             INT NOT NULL,
    updated_user_id             INT NOT NULL,
    created_at                  DATETIME NOT NULL,
    updated_at                  DATETIME NOT NULL,
    deleted_at                  DATETIME,
    CONSTRAINT PRIMARY KEY (id)
);


CREATE TABLE reservation.rooms
(
    id                          MEDIUMINT NOT NULL,
    name                        VARCHAR(30) NOT NULL,
    created_user_id             INT NOT NULL,
    updated_user_id             INT NOT NULL,
    created_at                  DATETIME NOT NULL,
    updated_at                  DATETIME NOT NULL,
    deleted_at                  DATETIME,
    CONSTRAINT PRIMARY KEY (id)
);


CREATE TABLE reservation.users
(
    id                          MEDIUMINT NOT NULL,
    account                     VARCHAR(30) NOT NULL,
    name                        VARCHAR(30),
    password                    VARCHAR(40),
    role_id                     MEDIUMINT NOT NULL,
    created_user_id             INT NOT NULL,
    updated_user_id             INT NOT NULL,
    created_at                  DATETIME NOT NULL,
    updated_at                  DATETIME NOT NULL,
    deleted_at                  DATETIME,
    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);


CREATE TABLE reservation.reservations
(
    id                          MEDIUMINT NOT NULL,
    title                       VARCHAR(30) NOT NULL,
    start                       DATETIME NOT NULL,
    end                         DATETIME NOT NULL,
    room_id                     MEDIUMINT NOT NULL,
    reserved_user_id            MEDIUMINT NOT NULL,
    created_user_id             INT NOT NULL,
    updated_user_id             INT NOT NULL,
    created_at                  DATETIME NOT NULL,
    updated_at                  DATETIME NOT NULL,
    deleted_at                  DATETIME,
    CONSTRAINT PRIMARY KEY (id),
    CONSTRAINT fk_reserved_user_id FOREIGN KEY (reserved_user_id) REFERENCES users (id),
    CONSTRAINT fk_room_id FOREIGN KEY (room_id) REFERENCES rooms (id)
);
