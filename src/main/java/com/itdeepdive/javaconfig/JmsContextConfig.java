package com.itdeepdive.javaconfig;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;
//cd JBOSS_HOME/bin/client
//mvn install:install-file -Dfile=jboss-client.jar -DgroupId=org.jboss -DartifactId=jboss-client -Dversion=6.4.0 -Dpackaging=jar

@Configuration
public class JmsContextConfig {
	private static final String JMS_CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";

	@Bean
	public JndiTemplate jndiTemplate() {

		final JndiTemplate template = new JndiTemplate();
		final Properties templateProperties = new Properties();
		templateProperties.setProperty("java.naming.factory.initial",
				"org.jboss.naming.remote.client.InitialContextFactory");
		templateProperties.setProperty("java.naming.provider.url",
				"remote://localhost:4447");
		templateProperties.setProperty("java.naming.security.principal",
				"admin");
		templateProperties.setProperty("java.naming.security.credentials",
				"password1!");
		template.setEnvironment(templateProperties);
		return template;
	}

	@Bean
	public ConnectionFactory jmsConnectionFactory() {

		final JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiTemplate(jndiTemplate());
		bean.setJndiName(JMS_CONNECTION_FACTORY_JNDI_NAME);
		try {
			bean.afterPropertiesSet();
		} catch (final Exception e) {
			throw new RuntimeException(
					"Failure looking up JMS connection factory from JNDI. JndiName="
							+ JMS_CONNECTION_FACTORY_JNDI_NAME, e);
		}
		return (ConnectionFactory) bean.getObject();
	}

	@Bean
	public Destination topicZ() {
		JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
		factoryBean.setJndiTemplate(jndiTemplate());
		factoryBean.setJndiName("/topic/Z");
		try {
			factoryBean.afterPropertiesSet();
		} catch (Exception e) {
			throw new RuntimeException(
					"Error getting /topic/Z from JNDI", e);
		}
		return (Destination) factoryBean.getObject();
	}

	@Bean
	public JndiObjectFactoryBean queueA() {
		JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
		factoryBean.setJndiTemplate(jndiTemplate());
		factoryBean.setJndiName("/queue/A");
		try {
			factoryBean.afterPropertiesSet();
		} catch (Exception e) {
			throw new RuntimeException(
					"Error getting /queue/A from JNDI", e);
		}
		return  factoryBean;
	}
	

	@Bean
	public JndiObjectFactoryBean queueB() {
		JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
		factoryBean.setJndiTemplate(jndiTemplate());
		factoryBean.setJndiName("/queue/B");
		try {
			factoryBean.afterPropertiesSet();
		} catch (Exception e) {
			throw new RuntimeException(
					"Error getting /queue/B from JNDI", e);
		}
		return factoryBean;
	}

	@Bean
	public JndiObjectFactoryBean queueC() {
		JndiObjectFactoryBean factoryBean = new JndiObjectFactoryBean();
		factoryBean.setJndiTemplate(jndiTemplate());
		factoryBean.setJndiName("/queue/C");
		try {
			factoryBean.afterPropertiesSet();
		} catch (Exception e) {
			throw new RuntimeException(
					"Error getting /queue/C from JNDI", e);
		}
		return factoryBean;
	}
	

	@Bean
	public DefaultMessageListenerContainer listenerA() {
		final DefaultMessageListenerContainer subscriber = new DefaultMessageListenerContainer();
		subscriber.setConnectionFactory(jmsConnectionFactory());
		subscriber.setDestination((Destination)queueA().getObject());
		subscriber.setMessageListener(new ListenerA());
		subscriber.setClientId("Client-A");
		return subscriber;
	}
	
	@Bean
	public DefaultMessageListenerContainer listenerB() {
		final DefaultMessageListenerContainer subscriber = new DefaultMessageListenerContainer();
		subscriber.setConnectionFactory(jmsConnectionFactory());
		subscriber.setDestination((Destination)queueB().getObject());
		subscriber.setMessageListener(new ListenerB());
		subscriber.setClientId("Client-B");
		return subscriber;
	}

	@Bean
	public DefaultMessageListenerContainer listenerC() {
		final DefaultMessageListenerContainer subscriber = new DefaultMessageListenerContainer();
		subscriber.setConnectionFactory(jmsConnectionFactory());
		subscriber.setDestination((Destination)queueC().getObject());
		subscriber.setMessageListener(new ListenerC());
		subscriber.setClientId("Client-C");
		return subscriber;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(jmsConnectionFactory());
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}

}
