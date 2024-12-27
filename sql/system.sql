DROP TABLE IF EXISTS `advice`;
CREATE TABLE `advice` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `advice` text,
                          `name` varchar(255) DEFAULT NULL,
                          `satisfaction` varchar(255) DEFAULT NULL,
                          `feedbackReasonString` text,
                          `create_time` datetime DEFAULT NULL,
                          `create_by` varchar(255) DEFAULT NULL,
                          `update_time` datetime DEFAULT NULL,
                          `update_by` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Sys_dept`;
CREATE TABLE `Sys_dept` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `dept_name` varchar(255) DEFAULT NULL,
                            `parent_id` int DEFAULT NULL,
                            `create_time` datetime DEFAULT NULL,
                            `create_by` varchar(255) DEFAULT NULL,
                            `update_time` datetime DEFAULT NULL,
                            `update_by` datetime DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Sys_role`;
CREATE TABLE `Sys_role` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `dept_id` int DEFAULT NULL,
                            `role_name` varchar(255) DEFAULT NULL,
                            `code` varchar(255) DEFAULT NULL,
                            `create_time` datetime DEFAULT NULL,
                            `create_by` varchar(255) DEFAULT NULL,
                            `update_time` datetime DEFAULT NULL,
                            `update_by` datetime DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Sys_user`;
CREATE TABLE `Sys_user` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `dept_id` int DEFAULT NULL,
                            `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                            `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                            `password` varchar(255) DEFAULT NULL,
                            `email` varchar(255) DEFAULT NULL,
                            `sex` varchar(5) DEFAULT NULL,
                            `phone` varchar(255) DEFAULT NULL,
                            `avatar` varchar(255) DEFAULT NULL,
                            `status` varchar(6) DEFAULT NULL,
                            `create_time` datetime DEFAULT NULL,
                            `create_by` int DEFAULT NULL,
                            `update_time` datetime DEFAULT NULL,
                            `update_by` int DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uniqe_account` (`account`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Sys_user_role`;
CREATE TABLE `Sys_user_role` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `user_id` int DEFAULT NULL,
                                 `role_id` int DEFAULT NULL,
                                 `create_time` datetime DEFAULT NULL,
                                 `create_by` varchar(255) DEFAULT NULL,
                                 `update_time` datetime DEFAULT NULL,
                                 `update_by` datetime DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
#超级管理员账号
INSERT INTO `Sys_user` (`id`, `dept_id`, `account`, `nickname`, `password`, `email`, `sex`, `phone`, `avatar`, `status`, `create_time`, `create_by`, `update_time`, `update_by`) VALUES (1, 27, 'admin', '超级管理员', 'admin', NULL, NULL, NULL, NULL, '启用', NULL, NULL, '2024-12-26 01:43:40', NULL);
