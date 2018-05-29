

CREATE TABLE ticket_info
(
    ticket_id varchar(32) NOT NULL,
    ticket_name varchar(64) NOT NULL COMMENT '机票名称',
    ticket_price decimal(8,2) NOT NULL COMMENT '单价',
    ticket_stock int NOT NULL COMMENT '余量',
    ticket_description varchar(64) COMMENT '描述',
    airline_type int NOT NULL COMMENT '航空公司编号',
    create_time timestamp DEFAULT current_timestamp NOT NULL,
    update_time timestamp DEFAULT current_timestamp on update current_timestamp,
    PRIMARY KEY (ticket_id)
) COMMENT = '机票表';


CREATE TABLE airline_info
(
    airline_id int NOT NULL AUTO_INCREMENT,
    airline_name varchar(64) NOT NULL COMMENT '航空公司名称',
    airline_type int NOT NULL COMMENT '航空公司编号',
    create_time timestamp DEFAULT current_timestamp NOT NULL,
    update_time timestamp DEFAULT current_timestamp on update current_timestamp,
    PRIMARY KEY (airline_id),
    unique key uqe_catagory_type (airline_type)
) COMMENT = '航空公司表';


create table order_master (
  order_id     varchar(32)            not null
    primary key,
  buyer_name   varchar(32)            not null comment '买家姓名',
  buyer_phone  varchar(32)            null comment '买家手机号',
  buyer_openid varchar(64)            not null comment '买家微信openid',
  order_amount decimal(8, 2)          null comment '订单总金额',
  order_status tinyint(3) default '0' not null comment '订单状态，默认0',
  pay_status   tinyint(3) default '0' not null comment '支付状态，默认0未支付',
  create_time timestamp DEFAULT current_timestamp NOT NULL,
  update_time timestamp DEFAULT current_timestamp on update current_timestamp
) COMMENT = '订单表';
create index idx_buyer_openid on order_master (buyer_openid);



CREATE TABLE order_detail
(
    detail_id varchar(32) PRIMARY KEY NOT NULL,
    order_id varchar(32) NOT NULL,
    ticket_id varchar(32) NOT NULL,
    ticket_name varchar(64) NOT NULL COMMENT '机票名称',
    ticket_price decimal(8,2) NOT NULL COMMENT '单价',
    ticket_quantity int NOT NULL,
    create_time timestamp DEFAULT current_timestamp NOT NULL,
    update_time timestamp DEFAULT current_timestamp on update current_timestamp
);
ALTER TABLE order_detail COMMENT = '订单详情表';





