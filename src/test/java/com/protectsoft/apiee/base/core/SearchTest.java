package com.protectsoft.apiee.base.core;

import com.protectsoft.apiee.base.entities.BaseEntity;
import com.protectsoft.apiee.util.PagedList;
import com.protectsoft.apiee.util.SearchUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Abraham Piperidis
 */
public class SearchTest {
    
   @Test
   public void testSearchDetailsMore() {
       List<LevelThreeEntity> list = getLevelThreeEntries();
       JsonObject json = Json.createObjectBuilder()
               //threeName:in the same object
               .add("threeName","proto")
               //id:In BaseEntity
               .add("id",3L)
               //twoint in the exact parent object
               .add("twoInt",66)
               .build();
       
       PagedList<? extends BaseEntity> res = SearchUtil.searchDetails(list, json);
       assertTrue(res.getFilteredList().size() == 4);
   }
   
   
   @Test
   public void testSortString() {
       List<LevelThreeEntity> list = getLevelThreeEntries();
       JsonObject json = Json.createObjectBuilder()
               .add("sort",Json.createArrayBuilder()
                       .add("+threeName").build()
               )
               .build();
        PagedList<? extends BaseEntity> res = SearchUtil.searchDetails(list, json);
        assertEquals("Avraam",((LevelThreeEntity)res.getFilteredList().get(0)).threeName);
        assertEquals("Braam",((LevelThreeEntity)res.getFilteredList().get(1)).threeName);
   }
   
   @Test
   public void testSortNumber() {
       List<LevelThreeEntity> list = getLevelThreeEntries();
       JsonObject json = Json.createObjectBuilder()
               .add("sort",Json.createArrayBuilder()
                       .add("-threeInt")
                       .build()
               )
               .build();
       PagedList<? extends BaseEntity> res = SearchUtil.searchDetails(list, json);
       assertEquals(125,((LevelThreeEntity)res.getFilteredList().get(0)).threeInt);
   }
   
   
   @Test
   public void testLimit() {
       List<LevelThreeEntity> list = getLevelThreeEntries();
       JsonObject json = Json.createObjectBuilder()
               .add("sort",Json.createArrayBuilder()
                       .add("+threeName").build()
               )
               .add("limit",4)
               .build();
        PagedList<? extends BaseEntity> res = SearchUtil.searchDetails(list, json);
        assertEquals(9,res.getOriginalSize().intValue());
        assertEquals("Avraam",((LevelThreeEntity)res.getFilteredList().get(0)).threeName);
   }
   
   
   @Test
   public void testOffset() {
       List<LevelThreeEntity> list = getLevelThreeEntries();
       JsonObject json = Json.createObjectBuilder()
               .add("sort",Json.createArrayBuilder()
                       .add("+id").build()
               )
               .add("limit",5)
               .add("offset",2)
               .build();
        PagedList<? extends BaseEntity> res = SearchUtil.searchDetails(list, json);
        assertEquals(9,res.getOriginalSize().intValue());
        //3 from the offset
        assertEquals(3,((LevelThreeEntity)res.getFilteredList().get(0)).getId().intValue());
        assertEquals(5,res.getFilteredList().size());
   }
   
   
   @Test
   public void testCompareDates() {
       List<LevelThreeEntity> list = getLevelThreeEntries();
       JsonObject json = Json.createObjectBuilder()
               .add("sort",Json.createArrayBuilder()
                       .add("-date").build()
               )             
               .build();
        PagedList<? extends BaseEntity> res = SearchUtil.searchDetails(list, json);
        assertEquals(8,((LevelThreeEntity)res.getFilteredList().get(0)).getId().intValue());
   }
   
    private List<LevelThreeEntity> getLevelThreeEntries() {
        List<LevelThreeEntity> list = new ArrayList<>();
        
        list.add(new LevelThreeEntity(7L,"stauros",false,15L,241.150,55,getDate(2017,10,05,10,10,10,00000000)));
        list.add(new LevelThreeEntity(8L,"Makaronada",false,25L,25.150,67,getDate(2017,10,20,10,10,10,00000000)));
        list.add(new LevelThreeEntity(1L,"protocol",false,1520L,20.150,11,getDate(2015,10,10,10,10,10,00000000)));
        list.add(new LevelThreeEntity(2L,"protocolBooks",false,151L,21.150,22,getDate(2017,05,10,10,10,10,00000000)));
        list.add(new LevelThreeEntity(3L,"onomateponimo",false,152L,22.150,33,getDate(2015,10,10,10,10,10,00000000)));
        list.add(new LevelThreeEntity(4L,"Avraam",false,153L,15.150,44,getDate(2015,10,10,10,10,10,00000000)));
        list.add(new LevelThreeEntity(5L,"piperidis",false,154L,24.150,55,getDate(2013,10,10,10,10,10,00000000)));
        list.add(new LevelThreeEntity(6L,"Μπρόκόλι",false,155L,25.150,66,getDate(2015,10,10,10,10,10,00000000)));
        list.add(new LevelThreeEntity(20L,"Braam",false,1485L,147.150,125,getDate(2015,10,10,10,10,10,00000000)));
        
        return list;
    }
   
   
   
  protected static Date getDate(int year,int month,int day,int hour,int min,int sec,int millis) {
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.MONTH, --month);
        date.set(Calendar.DAY_OF_MONTH, day);
        date.set(Calendar.HOUR_OF_DAY ,hour);
        date.set(Calendar.MINUTE, min);
        date.set(Calendar.SECOND,sec);
        date.set(Calendar.MILLISECOND, millis);
        return date.getTime();
   } 

   
   /**
    * there is no limitation in the depth of inheritance
    */
   public class LevelOneEntity extends BaseEntity {
       private String oneName;
       private boolean oneActive;
       private Long oneLong;
       private double oneDouble;
       private int oneInt;
       public Long id;
       
       public LevelOneEntity(){}
       
       public LevelOneEntity(Long id,String n,boolean a,Long l,double d,int i) {
           this.id = id;
           this.oneName = n;
           this.oneActive = a;
           this.oneLong = l;
           this.oneDouble = d;
           this.oneInt = i;
       }

        /**
         * @return the twoName
         */
        public String getOneName() {
            return oneName;
        }

        /**
         * @param oneName the twoName to set
         */
        public void setOneName(String oneName) {
            this.oneName = oneName;
        }

        /**
         * @return the twoActive
         */
        public boolean isOneActive() {
            return oneActive;
        }

        /**
         * @param oneActive the twoActive to set
         */
        public void setOneActive(boolean oneActive) {
            this.oneActive = oneActive;
        }

        /**
         * @return the twoLong
         */
        public Long getOneLong() {
            return oneLong;
        }

        /**
         * @param oneLong the twoLong to set
         */
        public void setOneLong(Long oneLong) {
            this.oneLong = oneLong;
        }

        /**
         * @return the twoDouble
         */
        public double getOneDouble() {
            return oneDouble;
        }

        /**
         * @param oneDouble the twoDouble to set
         */
        public void setOneDouble(double oneDouble) {
            this.oneDouble = oneDouble;
        }

        /**
         * @return the twoInt
         */
        public int getOneInt() {
            return oneInt;
        }

        /**
         * @param oneInt the twoInt to set
         */
        public void setOneInt(int oneInt) {
            this.oneInt = oneInt;
        }

        @Override
        public Long getId() {
            return id;
        }
   }
   
   public class LevelTwoEntity extends LevelOneEntity {
        private String twoName;
       private boolean twoActive;
       private Long twoLong;
       private double twoDouble;
       private int twoInt;
       
       public LevelTwoEntity(){}
       
       public LevelTwoEntity(Long id,String n,boolean a,Long l,double d,int i) {
           this.id = id;
           this.twoName = n;
           this.twoActive = a;
           this.twoLong = l;
           this.twoDouble = d;
           this.twoInt = i;
       }

        /**
         * @return the twoName
         */
        public String getTwoName() {
            return twoName;
        }

        /**
         * @param twoName the twoName to set
         */
        public void setTwoName(String twoName) {
            this.twoName = twoName;
        }

        /**
         * @return the twoActive
         */
        public boolean isTwoActive() {
            return twoActive;
        }

        /**
         * @param twoActive the twoActive to set
         */
        public void setTwoActive(boolean twoActive) {
            this.twoActive = twoActive;
        }

        /**
         * @return the twoLong
         */
        public Long getTwoLong() {
            return twoLong;
        }

        /**
         * @param twoLong the twoLong to set
         */
        public void setTwoLong(Long twoLong) {
            this.twoLong = twoLong;
        }

        /**
         * @return the twoDouble
         */
        public double getTwoDouble() {
            return twoDouble;
        }

        /**
         * @param twoDouble the twoDouble to set
         */
        public void setTwoDouble(double twoDouble) {
            this.twoDouble = twoDouble;
        }

        /**
         * @return the twoInt
         */
        public int getTwoInt() {
            return twoInt;
        }

        /**
         * @param twoInt the twoInt to set
         */
        public void setTwoInt(int twoInt) {
            this.twoInt = twoInt;
        }
   }
   
   public class LevelThreeEntity extends LevelTwoEntity {
        private String threeName;
       private boolean threeActive;
       private Long threeLong;
       private double threeDouble;
       private int threeInt;
       private Date date;
       
       public LevelThreeEntity(){}
       
       public LevelThreeEntity(Long id,String n,boolean a,Long l,double d,int i) {
           this.id = id;
           this.threeName = n;
           this.threeActive = a;
           this.threeLong = l;
           this.threeDouble = d;
           this.threeInt = i;
           super.setTwoInt(i);
       }
       
       public LevelThreeEntity(Long id,String n,boolean a,Long l,double d,int i,Date date) {
           this.id = id;
           this.threeName = n;
           this.threeActive = a;
           this.threeLong = l;
           this.threeDouble = d;
           this.threeInt = i;
           super.setTwoInt(i);
           this.date = date;
       }
       
       public Date getDate() {
           return this.date;
       }
       
       public void setDate(Date d){
           this.date = d;
       }

        /**
         * @return the twoName
         */
        public String getThreeName() {
            return threeName;
        }

        /**
         * @param oneName the twoName to set
         */
        public void setThreeName(String oneName) {
            this.threeName = oneName;
        }

        /**
         * @return the twoActive
         */
        public boolean isThreeActive() {
            return threeActive;
        }

        /**
         * @param oneActive the twoActive to set
         */
        public void setThreeActive(boolean oneActive) {
            this.threeActive = oneActive;
        }

        /**
         * @return the twoLong
         */
        public Long getThreeLong() {
            return threeLong;
        }

        /**
         * @param oneLong the twoLong to set
         */
        public void setThreeLong(Long oneLong) {
            this.threeLong = oneLong;
        }

        /**
         * @return the twoDouble
         */
        public double getThreeDouble() {
            return threeDouble;
        }

        /**
         * @param oneDouble the twoDouble to set
         */
        public void setThreeDouble(double oneDouble) {
            this.threeDouble = oneDouble;
        }

        /**
         * @return the twoInt
         */
        public int getThreeInt() {
            return threeInt;
        }

        /**
         * @param oneInt the twoInt to set
         */
        public void setThreeInt(int oneInt) {
            this.threeInt = oneInt;
        }
   }
   
    
}
