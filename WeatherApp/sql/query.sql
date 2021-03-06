create table app_user 
(
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) not null,
    email VARCHAR(100) not null,
    password VARCHAR(100) not null
);

create table weather_preferences
(
    user_id int,
    zip_code VARCHAR(10) not null,
    is_local boolean not null,
    name VARCHAR(100) not null,
    PRIMARY KEY (user_id, zip_code),
    FOREIGN KEY (user_id) 
    REFERENCES app_user(user_id)
    On DELETE CASCADE
);

drop table app_user;

delete from weather_preferences where user_id = 1;