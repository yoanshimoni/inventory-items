package com.example.rest.demorest.item;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	
	@Modifying
	@Query("UPDATE Item SET amount= amount + :amount WHERE itemNo = :itemNo")
	public void deposit(@Param("itemNo") Long itemNo, @Param("amount") int amount);
	
	@Modifying
	@Query("UPDATE Item SET amount= amount - :amount WHERE itemNo = :itemNo")
	public void withdrawal(@Param("itemNo") Long itemNo, @Param("amount") int amount);
}
