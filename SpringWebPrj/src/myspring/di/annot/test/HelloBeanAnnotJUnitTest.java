package myspring.di.annot.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import myspring.di.xml.Hello;
import myspring.di.xml.Printer;
import java.util.List;

public class HelloBeanAnnotJUnitTest {

	private ApplicationContext context;
	
	@Before
	public void init() {
		context = new GenericXmlApplicationContext("config/beans.xml");
						
	}
	@Test
	public void bean1() {
		Hello hello = (Hello) context.getBean("hello");
		assertEquals("Hello Spring", hello.sayHello());
		hello.print();
		System.out.println(hello.sayHello());
				
//		assertEquals(3, hello.getNames().size());
//		List<String> list = hello.getNames();
//		for (String value : list) {
//			System.out.println(value);
//		}
//		
//		Printer printer = (Printer) context.getBean("printer");
//		assertEquals("Hello Spring", printer.toString());
	}
	
//	@Test @Ignore
//	public void bean2() {
//		Printer printer = (Printer) context.getBean("printer");
//		Printer printer2 = context.getBean("printer",Printer.class);
//		
//		assertSame(printer, printer2);
//	}
}
