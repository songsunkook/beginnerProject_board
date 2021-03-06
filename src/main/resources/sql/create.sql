CREATE TABLE `beginner_project`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `account_id` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `nickname` VARCHAR(50) NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC) VISIBLE);

CREATE TABLE `beginner_project`.`board` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(200) NOT NULL,
  `content` VARCHAR(5000) NOT NULL,
  `user_id` INT NOT NULL,
  `likes` INT NOT NULL DEFAULT 0,
  `views` INT NOT NULL DEFAULT 0,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `beginner_project`.`comment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `article_id` INT NOT NULL,
  `content` VARCHAR(300) NOT NULL,
  `user_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted_at` TIMESTAMP NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `beginner_project`.`like` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `article_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`));
