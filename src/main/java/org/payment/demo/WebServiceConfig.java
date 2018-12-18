package org.payment.demo;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.ProviderBasedWsdl4jDefinition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.provider.DefaultConcretePartProvider;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean(name = "payment")
    public Wsdl11Definition defaultWsdl11Definition(XsdSchema paymentSchema) {
        MyWsdl11Definition wsdl11Definition = new MyWsdl11Definition();
        wsdl11Definition.setPortTypeName("RemitPaymentPort");
        wsdl11Definition.setServiceName("RemitPayment");
        wsdl11Definition.setRequestSuffix("");
        Properties soapActions = new Properties();
        soapActions.setProperty("RemitPayment", "http://webapp-linux-181204003823.azurewebsites.net/ws/");
        wsdl11Definition.setSoapActions(soapActions);
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("urn:Agregator");
        wsdl11Definition.setSchema(paymentSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("payment.xsd"));
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this is the package name specified in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("hello.wsdl");
        return marshaller;
    }

    @Bean
    public TransactionService transactionService(Jaxb2Marshaller marshaller) {
        TransactionService client = new TransactionService();
        client.setDefaultUri("http://webapp-linux-181205003304.azurewebsites.net/bank/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
