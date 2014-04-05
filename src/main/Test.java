package main;

import java.util.LinkedList;
import java.util.List;

import main.util.CloningUtil;

public class Test {

	
	public static void main(String[] args){
		List<Integer> test1 = new LinkedList<Integer>();
		test1.add(0);
		CloningUtil.clone(test1);
		List<TestObject1> test2 = new LinkedList<TestObject1>();
		test2.add(new TestObject1(0, 1, 2));
		CloningUtil.clone(test2);
	}
}
