CREATE TABLE `stock` (
  `product_id` bigint(11) NOT NULL COMMENT '商品id',
  `num` int(1) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4