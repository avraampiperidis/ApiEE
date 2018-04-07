# ApiEE Java EE Web Framework
a Java EE Web Framework  to develop java ee web apps fast.
jax-rs,CDI,EJB,JPA2 <br>
[![Build Status](https://travis-ci.org/avraampiperidis/ApiEE.svg?branch=master)](https://travis-ci.org/avraampiperidis/ApiEE)

- [x] On Early development! 
- [x] Update README.md with examples/tutorials(Jax-rs,Ejb,Master-Details)
- [x] Master Detail functionality
- [x] Search on details functionality
- [x] Basic junit tests/iTtests
- [x] Create on details (1t1,1tM,MtM)
- [x] Edit/Delete on details 
- [ ] IT Test CRUD Master/Details
- [ ] Test @Transform/TransformRelation
- [ ] Generic Base Search with Criteria
- [ ] Create appropriate exception classes
- [ ] Run/Test against java EE various implementations
- [ ] Support Hibernate and Eclipselink
- [ ] Support Spring
- [ ] Documentation/api
- [ ] Publish on maven central


<b>
* The following code examples are from my draft/working version witch i
havent updated here yet! <br>
* Also these samples are ment to show what the framework is about
and what it WILL do. <br>
* So this framework is NOT completed yet! It's in Working progress
</b> <br>
# TODO List and preview . <br>
<b>A lot of the stuff below will change and will be implemented in different way.</b><br>
<br>
## A fast top-down preview ,with two examples
#### Entities
* All entities must extend BaseEntityAUTO or BaseEntitySequence class. <br>
* BaseEntityAUTO it excepts an auto increment id. <br>
* BaseEntitySequence it  excepts a sequence generator for the id.<br>
* The primary id name of the tables must by id. <br>

###### Example Entity Class
A vert simple and minimalistic entity class and its relations. <br>
This entity has some properties the framework provides which is
* The implementation of the TransformableRelation interface
* The TransformRelation annotation, which is used by the MessageBodyReader Provider(for Jax-rs discovery)
* will be used later in the examples. <br>
```java

@Entity
@Table(name = "DM_ORGANIZATION_UNIT")
@XmlRootElement
public class OrganizationUnit extends BaseEntity implements Serializable,TransformableRelation {

    private static final long serialVersionUID = 1L;
    //Two simple table fields
    @Column(name = "USRCODE")
    private String usrcode;
    @Column(name = "DESCRIPTION")
    private String description;

    //Ok this is a foreign key showing to the same table 
    //so every oranization has a parent organization
    @JoinColumn(name = "PID", referencedColumnName = "ID")
    @ManyToOne
    private OrganizationUnit parentUnit;
    
    //and one organization has many child organizations
    @OneToMany(mappedBy = "parentUnit")
    private List<OrganizationUnit> organizationChartList;
    
    
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    //by using this annotation and implementing TransformableRelation interface
    //The Provider will try to convert the id and set unitType
    //this is very usefull when sending json between back-front
    @TransformRelation
    private OrganizationType unitType;
    
    public OrganizationUnit() {
    }

    @XmlTransient
    public List<OrganizationUnit> getOrganizationUnitList() {
        return organizationChartList;
    }

    public void setOrganizationUnitList(List<OrganizationUnit> organizationChartList) {
        this.organizationChartList = organizationChartList;
    }

   ...
   setters
   getters
   ...

	//prevent mapping of javabean to xml
    @XmlTransient
    public OrganizationType getUnitType() {
        return unitType;
    }

    //We want to send only the id
    //when we get the result back
    //and its unitType:1L
    //the Provider will to its part and convert it
    @XmlElement(name="unitType")
    public Long getTypeId() {
        if(unitType != null)
            return unitType.getId();
        return null;
    }

}

```
###### Example Ejb service
A simple ejb service for our Organization that extending ApiEEFacade abstract class <br>
All the ejb's extending ApiEEFacade have the following functionallity
* Master-Detail CRUD Operations
* read range
* validation
* move/transfer
* master-Detail search

```java

@Stateless
public class OrganizationUnitFacade extends ApiEEFacade<OrganizationUnit> {

	//basically here we are registering
    //the master detail relationship
    public OrganizationUnitFacade() {
        super(OrganizationUnit.class);
        
        //master detail
		//In todo list
		//Set MasterDetail
//        Function<OrganizationUnit,List<OrganizationUnit>> list = (OrganizationUnit t) -> t.getOrganizationUnitList();
//        
//        BiConsumer<OrganizationUnit,OrganizationUnit> consumer = (OrganizationUnit t, OrganizationUnit u) -> {
//            u.setParentUnit(t);           
//        };
        
        
        //and register it
        registry.register(OrganizationUnit.class, OrganizationUnit.class, 
                list, consumer, 
                this, MasterDetailPayload.MoveOptions.ORPHANS_ALLOWED);
    }
    
}

```

###### Example Jax-Rs endpoint
A simple jax-rs endpoint for our organization service <br>
every endpoint extending GenericResource have access to the following functionality
* All CRUD by default
* Details CRUD (SubResources) are scoped based on the master
* Search
By extending GenericResource we have all CRUD operations by default for <br> out entity
```java

@Path("units")
public class OrganizationUnitResource extends ApiEEResource<OrganizationUnit> {

	@Inject
	public OrganizationUnitResource(OrganizationUnitFacade service) {
		super(service);
	}
    
    @Path("{id}/units")
    public ApiEESubResource subResource(@PathParam("id") Long id) {
        return new SubResource(id);
    } 
    
    //CRUD Operations 
    //for example read all will get only the parents units
    //the same applies for search , createDetail , ...
    public class SubResource extends ApiEESubResource<OrganizationUnit,OrganizationUnit> {

        public SubResource(Long id) {
            super(id, service, OrganizationUnit.class);
        }
        
        //will continue to deep in as big is the relation in the database
        //for example the url could end up somethink like
        // units/2/units/3/units/10/units
        @Path("{id}/units")   
        public ApiEESubResource subResource(@PathParam("id") Long id) {
            return new SubResource(id);
        } 
    }

}

```
## Second example from the apieeweb_sample folder

<b>Lets say we have a department entity.</b>
```java
@Entity
@Table(name = "DEPARTMENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
})
public class Department extends BaseEntityAUTO implements TransformableRelation {
	...
    //One department may have a parent department
    //We want id to object convertion so we annotate it  @TransformRelatio.
    @TransformRelation
    @JoinColumn(name = "PARENT_ID",referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department parent;
    
    //and may have many child departments below.
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Department> childs;
    
    //Actualy one employ can work in many departments?
    //in this case he can.
    @ManyToMany
    @JoinTable(name = "DEP_EMPS",
            joinColumns=@JoinColumn(name="DEPARTMENT_ID",referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="EMPLOYEE_ID",referencedColumnName="ID"))
    private List<Employee> employees;
    
    //We want id to object convertion.
    @TransformRelation
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organization organization;
    ...
}
```
<b>And we make the following service</b>
```java
@Stateless
public class DepartmentFacade extends ApiEEFacade<Department> {
    public DepartmentFacade() {
        super(Department.class);
		//todo create masterDetail relation
		masterDetailService.register(..M master..D detail.);     
    }
}
```
<b>And we simply expose an endpoint for that service.</b>
```java
@Path("departments")
public class DepartmentResource extends ApiEEResource<Department> {

    @Inject
	public DepartmentResource(DepartmentFacade service) {
		super(service);
	}
    
    @Path("{id}/departments")
    public ApiEESubResource subResource(@PathParam("id") Long id) {
        return new SubResource(id);
    }
    
    public class SubResource extends ApiEESubResource<Department,Department> {
        
        public SubResource(Long id) {
            super(id,service,Department.class);
        }
        
        @Path("{id}/departments")
        public ApiEESubResource subResource(@PathParam("id") Long id) {
            return new SubResource(id);
        }
    }
}
```
#### Todo expected Results
<b>And lets see what we can do with this</b>. <br>
<b>GET</b> ```departments/``` -> will return a json list with all department objects.<br>
Create a department.(lets suppose we have an organization with id 1)<br>
<b>POST</b> Request in ```departments/``` with body
```json
{
       "description":"Human And Resources DEPT 01",
       "organization":1
}
```
we have the following response if the object is created successfully
```json
{
    "type": "department",
    "id": 1,
    "description": "Human And Resources DEPT 01",
    "organization": 1
}
```
same with <b>PUT</b> But we must specify the id in url
ex. ```departments/1```
```json
{
    "type": "department",
    "id": 1,
    "description": "Human And Resources DEPT 01",
    "organization": 1
}
```
<b>DELETE</b> request in ```departments/1```

Lets Create a SubDepartment for the previous department we created. <br>
If we call <b>GET</b> at ```department/1/departments ```   -> we get an empty list simple because no department is under this one.<br>
So we need to create a department with parent the one with id=1 <br>
We can do that we a <b>POST</b> at ```departments/1/departments``` <br>
and body data.
```json
{
    "description": "Human And Resources Finance",
    "organization": 1
}
```
Note we do not specify the parent, the framework will do that. <br>
And we get the following response.
```json
{
    "type": "department",
    "id": 2,
    "description": "Human And Resources Finance",
    "organization": 1,
    "parent": 1
}
```
Now lets suppose we want a department for the SubDepartment.<br>
We can do that following the same logic.
a <b>POST</b> at ```departments/1/departments/2/departments``` <br>
and body data.
```json
{
    "description": "Human And Resources Finance And Analysis.",
    "organization": 1
}
```
And we get the following response.
```json
{
    "type": "department",
    "id": 3,
    "description": "Human And Resources Finance And Analysis.",
    "organization": 1,
    "parent": 2
}
```
<b>Move a child Department</b>
Lets move the department with id=3 from parent 2 to parent 1<br>
We simply make a <b>POST</b> at ```departments/1/departments/2/departments/3?moveTo=1```

<b>SEARCH</b> <br>
We can search with sort/filtering both the master and as well the details <br>
All endpoints have search capabilities.<br>
A simple search is done with a <b>POST</b> following a search endpoint<br>
eg. ```departments/search``` <br>
with body data <br>
```json
{
	"limit":2,
	"offset":0,
	"description":"Huma"
}
```
more advanced search can contain
```json
{
	"limit":2,
	"offset":0,
	"sort":[
			"+description"
		]
}
```
##### JNDI EJB lookup helper
- [x] on development <br>
A easier way to access and lookup ejb's.<br>
A wrapper around Oracle's recommend way (```http://www.oracle.com/technetwork/java/servicelocator-137181.html```) <br>
See package for on going work ```com.protectsoft.apiee.api.control``` <br>

<br>
<b>
that was a fast showcase of the framework.<br>
It need's more work and more to detail documentation.<br>
I will update it when i will have free time.
</b>
