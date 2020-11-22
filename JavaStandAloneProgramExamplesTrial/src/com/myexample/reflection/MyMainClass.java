package com.myexample.reflection;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.myexample.reflection.Employee.MyAnnotation;

public class MyMainClass {
	private static final String PADDING_MEMBER_DETAILS = "   ";
	private static final String PADDING_MODIFIER_DETAILS = "      ";
	private static final String PADDING_METHOD_PARAMETER_DETAILS = "      ";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream myPrintStream = System.out;

		myPrintStream.println("**************************************");
		myPrintStream.println("* Employee class information.");
		myPrintStream.println("**************************************");
		try {
			/* Class Name. */
			Class<?> myEmployeeClass = Class.forName("com.myexample.reflection.Employee");
			myPrintStream.println("# Class Name: " + myEmployeeClass.getName());
			
			/* Interface details. */
			Class<?>[] myEmployeeInterfaces = myEmployeeClass.getInterfaces();
			for (int indexInterfaces = 0; indexInterfaces < myEmployeeInterfaces.length; indexInterfaces++) {
				myPrintStream.println(PADDING_MEMBER_DETAILS + "~ Interface Name: " + myEmployeeInterfaces[indexInterfaces].getName());
			}
			
			/* Class Constructor details. */
			Constructor<?>[] myEmployeeClassConstructors = myEmployeeClass.getConstructors();
			for (int indexConstructor = 0; indexConstructor < myEmployeeClassConstructors.length; indexConstructor++) {
				myPrintStream.println(PADDING_MEMBER_DETAILS + ". Constructor Name: " + myEmployeeClassConstructors[indexConstructor].getName());
				/* annotation details. */
				printAnnotationDetails(myPrintStream,myEmployeeClassConstructors[indexConstructor].getAnnotations());
				/* modifier details. */
				printModifierDetails(myPrintStream, myEmployeeClassConstructors[indexConstructor].getModifiers());
				/* parameter details. */
				printParamterDetails(myPrintStream, myEmployeeClassConstructors[indexConstructor].getParameterTypes());
				
			}
			
			/* Class field details. */
			Field[] myEmployeeFields = myEmployeeClass.getDeclaredFields();
			for (int indexFields = 0; indexFields < myEmployeeFields.length; indexFields++) {
				myPrintStream.println(PADDING_MEMBER_DETAILS + "! Field Name: " + myEmployeeFields[indexFields].getName());
				/* annotation details. */
				printAnnotationDetails(myPrintStream,myEmployeeFields[indexFields].getAnnotations());
				/* modifier details. */
				printModifierDetails(myPrintStream, myEmployeeFields[indexFields].getModifiers());
			}
			
			/* Class method details. */
			Method[] myEmployeeMethods = myEmployeeClass.getMethods();
			for (int indexMethods = 0; indexMethods < myEmployeeMethods.length; indexMethods++) {
				myPrintStream.println(PADDING_MEMBER_DETAILS + "* Method Name: " + myEmployeeMethods[indexMethods].getName());
				/* annotation details. */
				printAnnotationDetails(myPrintStream,myEmployeeMethods[indexMethods].getAnnotations());
				/* modifier details. */
				printModifierDetails(myPrintStream, myEmployeeMethods[indexMethods].getModifiers());
				/* parameter details. */
				printParamterDetails(myPrintStream, myEmployeeMethods[indexMethods].getParameterTypes());
			}
			
			/****************************************************************
			 * Trying to instantiate the employee class to get an object and
			 * then to run one of the methods.
			 ****************************************************************/
			Object myEmployee = myEmployeeClass.newInstance();
			/* Running setter methods. */
			for (int indexMethods = 0; indexMethods < myEmployeeMethods.length; indexMethods++) {
				String methodName = myEmployeeMethods[indexMethods].getName();
				try {				
					if (methodName.equals("setEmployeeID")) {
						myEmployeeMethods[indexMethods].invoke(myEmployee, new Object [] {1001});
						
					}
					if (methodName.equals("setFirstName")) {
						myEmployeeMethods[indexMethods].invoke(myEmployee, new Object [] {"Salma"});
					}
					if (methodName.equals("setMiddleName")) {
						myEmployeeMethods[indexMethods].invoke(myEmployee, new Object [] {"L"});
					}
					if (methodName.equals("setLastName")) {
						myEmployeeMethods[indexMethods].invoke(myEmployee, new Object [] {"Hayak"});
					}
				} catch (InvocationTargetException myInvocationTargetException) {
					myInvocationTargetException.printStackTrace();
				}
			}
			
			myPrintStream.println(myEmployee);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}/* catch (InvocationTargetException e) {
			e.printStackTrace();
		}*/ catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	private static void printAnnotationDetails(PrintStream aPrintStream,
			Annotation[] anAnnotations) {
		for (int indexAnnotations = 0; indexAnnotations < anAnnotations.length; indexAnnotations++) {
			if (anAnnotations[indexAnnotations] instanceof MyAnnotation) {
				MyAnnotation myAnnotation = (MyAnnotation) anAnnotations[indexAnnotations];
				aPrintStream.println(PADDING_MODIFIER_DETAILS + "? Annotation: name='" + myAnnotation.name()
											+ "' value='" + myAnnotation.value() + "'");
			}
			else {
				aPrintStream.println(PADDING_MODIFIER_DETAILS + "? Annotation: " + anAnnotations[indexAnnotations].toString());
			}
		}
	}

	private static void printModifierDetails(PrintStream aPrintStream, int aModifiers) {
		if (Modifier.isAbstract(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Abstract");
		}
		if (Modifier.isFinal(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Final");
		}
		if (Modifier.isInterface(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Interface");
		}
		if (Modifier.isNative(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Native");
		}
		if (Modifier.isPrivate(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Private");
		}
		if (Modifier.isProtected(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Protected");
		}
		if (Modifier.isPublic(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Public");
		}
		if (Modifier.isStatic(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Static");
		}
		if (Modifier.isStrict(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Strict");
		}
		if (Modifier.isSynchronized(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Synchronized");
		}
		if (Modifier.isTransient(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Transient");
		}
		if (Modifier.isVolatile(aModifiers)) {
			aPrintStream.println(PADDING_MODIFIER_DETAILS + "> Modifier: Volatile");
		}
	}
	
	private static void printParamterDetails(PrintStream aPrintStream, Class<?>[] aClass) {
		for (int indexConstructorParameter = 0; indexConstructorParameter < aClass.length; indexConstructorParameter++) {
			aPrintStream.println(PADDING_METHOD_PARAMETER_DETAILS + "- Parameter type: " + aClass[indexConstructorParameter].getSimpleName());
		}
	}

}
