drop table if exists `users`;

drop table if exists `todos`;

create table `users`
(
    `id` BIGINT not null auto_increment,
    `name` VARCHAR(100) not null,
    `email` VARCHAR(150) not null unique,
    `password` VARCHAR(250) not null,
    `created_at` datetime not null default current_timestamp,
    `updated_at` datetime not null default current_timestamp on update CURRENT_TIMESTAMP,
    primary key (`id`)
) engine=InnoDB;

create table `todos`
(
  `id` BIGINT not null auto_increment,
  `title` VARCHAR(150) not null,
  `description` TEXT,
  `completed` BOOLEAN default false,
  `user_id` BIGINT not null,
  `created_at` datetime not null default current_timestamp,
  `updated_at` datetime not null default current_timestamp on update CURRENT_TIMESTAMP,
  primary key (`id`),
  constraint `fk_todo_user` foreign key (`user_id`) references `users`(`id`) on delete cascade
) engine=InnoDB;