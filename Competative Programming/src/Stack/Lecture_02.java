package Stack;

import java.util.HashMap;
import java.util.Stack;

public class Lecture_02 {

	public static HashMap<Character, Integer> precedence = new HashMap<Character, Integer>();
	public static HashMap<Character, Boolean> associativity = new HashMap<Character, Boolean>();

	public static void main(String[] args) {

		precedence.put('^', 1);
		precedence.put('*', 2);
		precedence.put('/', 2);
		precedence.put('+', 3);
		precedence.put('-', 3);

		associativity.put('^', false);
		associativity.put('*', true);
		associativity.put('/', true);
		associativity.put('+', true);
		associativity.put('-', true);

		System.out.println(InfixToPostfix("K+L-M*N+(O^P)*W/U/V*T+Q"));
		System.out.println(InfixToPostfix("((((K+L)-(M*N))+(((((O^P)*W)/U)/V)*T))+Q)"));
		System.out.println(InfixToPrefix("K+L-M*N+(O^P)*W/U/V*T+Q"));
		System.out.println(InfixToPrefix("((((K+L)-(M*N))+(((((O^P)*W)/U)/V)*T))+Q)"));
		System.out.println(PostfixToInfix("KL+MN*-OP^W*U/V/T*+Q+"));
		System.out.println(PrefixToInfix("++-+KL*MN*//*^OPWUVTQ"));
		System.out.println(PostfixToPrefix("KL+MN*-OP^W*U/V/T*+Q+"));
		System.out.println(PrefixToPostfix("++-+KL*MN*//*^OPWUVTQ"));
	}

	public static String InfixToPostfix(String infix) {

		Stack<Character> stack = new Stack<Character>();
		String postfix = "";

		for (char ch : infix.toCharArray()) {
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
				postfix += ch;
			} else {
				if (stack.isEmpty() || stack.peek() == '(') {
					stack.push(ch);
				} else if (ch == '(') {
					stack.push(ch);
				} else if (ch == ')') {
					while (!stack.isEmpty() && stack.peek() != '(')
						postfix += stack.pop();
					stack.pop();
				} else if (precedence.get(stack.peek()) > precedence.get(ch)) {
					stack.push(ch);
				} else if (precedence.get(stack.peek()) < precedence.get(ch)) {
					while (!stack.isEmpty() && precedence.get(stack.peek()) <= precedence.get(ch))
						postfix += stack.pop();
					stack.push(ch);
				} else if (precedence.get(stack.peek()) == precedence.get(ch)) {
					if (associativity.get(ch))
						postfix += stack.pop();
					stack.push(ch);
				}
			}
		}
		while (!stack.isEmpty())
			postfix += stack.pop();

		return postfix;
	}

	public static String InfixToPrefix(String infix) {

		StringBuilder str = new StringBuilder(infix);
		str = str.reverse();
		infix = str.toString();

		Stack<Character> stack = new Stack<Character>();
		String prefix = "";

		for (char ch : infix.toCharArray()) {
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
				prefix += ch;
			} else {
				if (stack.isEmpty() || stack.peek() == ')') {
					stack.push(ch);
				} else if (ch == ')') {
					stack.push(ch);
				} else if (ch == '(') {
					while (!stack.isEmpty() && stack.peek() != ')')
						prefix += stack.pop();
					stack.pop();
				} else if (precedence.get(stack.peek()) > precedence.get(ch)) {
					stack.push(ch);
				} else if (precedence.get(stack.peek()) < precedence.get(ch)) {
					while (!stack.isEmpty() && precedence.get(stack.peek()) < precedence.get(ch))
						prefix += stack.pop();
					stack.push(ch);
				} else if (precedence.get(stack.peek()) == precedence.get(ch)) {
					if (!associativity.get(ch))
						prefix += stack.pop();
					stack.push(ch);
				}
			}
		}

		while (!stack.isEmpty())
			prefix += stack.pop();

		StringBuilder res = new StringBuilder(prefix);
		res = res.reverse();
		prefix = res.toString();

		return prefix;
	}

	public static String PostfixToInfix(String postfix) {

		Stack<String> stack = new Stack<>();
		for (char ch : postfix.toCharArray()) {
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
				stack.push("" + ch);
			} else {
				String val1 = stack.pop();
				String val2 = stack.pop();
				stack.push("(" + val2 + ch + val1 + ")");
			}
		}

		return stack.peek();
	}

	public static String PrefixToInfix(String prefix) {

		StringBuilder str = new StringBuilder(prefix);
		str = str.reverse();
		prefix = str.toString();

		Stack<String> stack = new Stack<>();
		for (char ch : prefix.toCharArray()) {
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
				stack.push("" + ch);
			} else {
				String val1 = stack.pop();
				String val2 = stack.pop();
				stack.push("(" + val1 + ch + val2 + ")");
			}
		}

		return stack.peek();
	}

	public static String PostfixToPrefix(String postfix) {

		Stack<String> stack = new Stack<>();
		for (char ch : postfix.toCharArray()) {
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
				stack.push("" + ch);
			} else {
				String val1 = stack.pop();
				String val2 = stack.pop();
				stack.push(ch + val2 + val1);
			}
		}
		return stack.peek();

	}

	public static String PrefixToPostfix(String prefix) {
		StringBuilder str = new StringBuilder(prefix);
		str = str.reverse();
		prefix = str.toString();

		Stack<String> stack = new Stack<>();
		for (char ch : prefix.toCharArray()) {
			if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')) {
				stack.push("" + ch);
			} else {
				String val1 = stack.pop();
				String val2 = stack.pop();
				stack.push(val1 + val2 + ch);
			}
		}
		return stack.peek();
	}
}
