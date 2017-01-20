LOCK TABLES `book` WRITE;
INSERT INTO `book` VALUES (12345,'Design Patterns'),(23456,'Developing Java Applications');
UNLOCK TABLES;

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES ('koyya','123456',1,1,1,1),('krishna','123456',1,1,1,1),('mohan','123456',1,1,1,1);
UNLOCK TABLES;