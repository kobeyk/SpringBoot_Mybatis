package com.appleyk.dict;

/**
 * 查询过滤器枚举类型
 * @author appleyk
 *
 */
public enum FilterTypeEnum {

	USER("用户filter", 0);


	private final String name;
	private Integer value;

	FilterTypeEnum(final String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * 根据value获取枚举对象
	 * 
	 * @param value
	 * @return
	 */
	public static FilterTypeEnum getEnum(int value) {
		for (FilterTypeEnum ftypeEnum : FilterTypeEnum.values()) {
			if (ftypeEnum.getValue() == value) {
				return ftypeEnum;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
}
