package com.website.springmvc.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.website.springmvc.entities.Address;
import com.website.springmvc.entities.Author;
import com.website.springmvc.entities.Bill;
import com.website.springmvc.entities.BillDetail;
import com.website.springmvc.entities.Category;
import com.website.springmvc.entities.City;
import com.website.springmvc.entities.Comic;
import com.website.springmvc.entities.Contact;
import com.website.springmvc.entities.District;
import com.website.springmvc.entities.FavoriteList;
import com.website.springmvc.entities.News;
import com.website.springmvc.entities.OrderStatus;
import com.website.springmvc.entities.PublishCompany;
import com.website.springmvc.entities.Role;
import com.website.springmvc.entities.Users;



@EnableTransactionManagement
@Configuration
public class SpringDatabaseConfig extends WebMvcConfigurerAdapter {
	@Bean
	public LocalSessionFactoryBean sessionFactory(BasicDataSource dataSource) {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setPackagesToScan(new String[] { "com.website.springmvc.entities" });
		sessionFactory.setAnnotatedClasses(Author.class, Bill.class, BillDetail.class, Category.class, 
				Comic.class, Contact.class, News.class, OrderStatus.class, FavoriteList.class,
				PublishCompany.class, Role.class, Users.class, Address.class, City.class, District.class);
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", false);
		properties.put("hibernate.format_sql", true);
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	@Bean(name = "dataSource")
	public BasicDataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/manga?zeroDateTimeBehavior=convertToNull");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setInitialSize(10);
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}