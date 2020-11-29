package  com.example.rest.demorest.item;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Item {
	
	@Id
	@GeneratedValue
	private Long itemNo;
	
	@Size(min=1, message="Name should have at least 1 characters")
	private String name;
	
	private int amount;
	
	private String inventoryCode;

	
	protected Item() {
	}

	public Item(long itemNo, @Size(min = 1, message = "Name should have at least 1 characters") String name,
			int amount, String inventoryCode) {
		super();
		this.itemNo = itemNo;
		this.name = name;
		this.amount = amount;
		this.inventoryCode = inventoryCode;
	}


	public Long getItemNo() {
		return itemNo;
	}


	public String getName() {
		return name;
	}


	public int getAmount() {
		return amount;
	}


	public String getInventoryCode() {
		return inventoryCode;
	}


	@Override
	public String toString() {
		return "Item [itemNo=" + itemNo + ", name=" + name + ", amount=" + amount + ", inventoryCode=" + inventoryCode
				+ "]";
	}

}
