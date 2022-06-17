package dao.mypage;

public class RefundData {
	
	private int num;
	private String refund_bookId;
	private String refund_bookName;
	private String refund_author;
	private int refund_sale;
	private String refund_mem;
	private String refund_date;
	private int refund_qty;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getRefund_bookId() {
		return refund_bookId;
	}
	public void setRefund_bookId(String refund_bookId) {
		this.refund_bookId = refund_bookId;
	}
	public String getRefund_bookName() {
		return refund_bookName;
	}
	public void setRefund_bookName(String refund_bookName) {
		this.refund_bookName = refund_bookName;
	}
	public String getRefund_author() {
		return refund_author;
	}
	public void setRefund_author(String refund_author) {
		this.refund_author = refund_author;
	}
	public int getRefund_sale() {
		return refund_sale;
	}
	public void setRefund_sale(int refund_sale) {
		this.refund_sale = refund_sale;
	}
	public String getRefund_mem() {
		return refund_mem;
	}
	public void setRefund_mem(String refund_mem) {
		this.refund_mem = refund_mem;
	}
	public String getRefund_date() {
		return refund_date;
	}
	public void setRefund_date(String refund_date) {
		this.refund_date = refund_date;
	}
	public int getRefund_qty() {
		return refund_qty;
	}
	public void setRefund_qty(int refund_qty) {
		this.refund_qty = refund_qty;
	}
	@Override
	public String toString() {
		return "RefundData [num=" + num + ", refund_bookId=" + refund_bookId + ", refund_bookName=" + refund_bookName
				+ ", refund_author=" + refund_author + ", refund_sale=" + refund_sale + ", refund_mem=" + refund_mem
				+ ", refund_date=" + refund_date + ", refund_qty=" + refund_qty + "]";
	}
	
	
}
