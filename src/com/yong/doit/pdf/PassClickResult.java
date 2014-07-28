/*
 * File:PassClickResult.java
 * Date:2014-3-10下午3:45:40
 *
 * 四川长虹网络科技有限责任公司 (智能应用研发部)© 版权所有 
 */
package com.yong.doit.pdf;

/**
 * @author yonkers
 */
public class PassClickResult {
	public final boolean changed;

	public PassClickResult(boolean _changed) {
		changed = _changed;
	}

	public void acceptVisitor(PassClickResultVisitor visitor) {
	}
}
