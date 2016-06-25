package com.panos.katabank.ocr;

/**
 * The object to contain the characters of each line of the represented number
 * 
 * @author panos
 *
 */
public class BarsDigit {

	/**
	 * The characters at the top line
	 */
	private String top;
	
	/**
	 * The characters at the middle line
	 */
	private String middle;
	
	/**
	 * The characters of the bottom line
	 */
	private String bottom;

	/**
	 * Main constructor
	 * 
	 * @param top
	 *            - The 3 characters from the first line
	 * @param middle
	 *            - The 3 characters from the second line
	 * @param bottom
	 *            - The 3 characters from the third line
	 */
	public BarsDigit(String top, String middle, String bottom) {
		super();
		this.top = top;
		this.middle = middle;
		this.bottom = bottom;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bottom == null) ? 0 : bottom.hashCode());
		result = prime * result + ((middle == null) ? 0 : middle.hashCode());
		result = prime * result + ((top == null) ? 0 : top.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BarsDigit other = (BarsDigit) obj;
		if (bottom == null) {
			if (other.bottom != null)
				return false;
		} else if (!bottom.equals(other.bottom))
			return false;
		if (middle == null) {
			if (other.middle != null)
				return false;
		} else if (!middle.equals(other.middle))
			return false;
		if (top == null) {
			if (other.top != null)
				return false;
		} else if (!top.equals(other.top))
			return false;
		return true;
	}

	// Getters & Setters
	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getMiddle() {
		return middle;
	}

	public void setMiddle(String middle) {
		this.middle = middle;
	}

	public String getBottom() {
		return bottom;
	}

	public void setBottom(String bottom) {
		this.bottom = bottom;
	}

}
