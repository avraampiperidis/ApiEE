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
- [x] Support xml.bind(JAXB) for JavaEE 7 and Jsonb(JSR 367) for JavaEE 8
- [ ] IT Test CRUD Master/Details
- [ ] Test @Transform/TransformRelation
- [ ] Generic Base Search with Criteria Api
- [ ] Create appropriate exception classes
- [ ] Run/Test against java EE various implementations
- [ ] Support Hibernate and Eclipselink
- [ ] Support Spring
- [ ] Documentation/api
- [x] Publish on maven central

Maven 
``` maven
<dependency>
      <groupId>com.protectsoft</groupId>
      <artifactId>apiee</artifactId>
      <version>0.3</version>
 </dependency>
```

* The following code examples are from my draft/working version witch<br>
* Also these samples are ment to show what the framework is about
and what it is already doing.<br>
* See Sample project (apieeweb) witch contains all the examples <br>
* This framework is NOT completed yet! It's in Working progress
 <br>
## A fast top-down preview ,with two examples 
* * <b>Your StartUp class must extend BaseConfig for provider registration</b> <br>
for example <br>
```java
@ApplicationPath("api")
public class Startup extends BaseConfig {  
    public Startup() {
        packages("you.package.endpoints;");       
    }
}
```

###### Example Entity Class
A vert simple and minimalistic entity class and its relations. <br>
This entity has some properties the framework provides which is
* The implementation of the Transform interface
* The TransformBean annotation, which is used by the MessageBodyReader Provider(for Jax-rs discovery)
* will be used later in the examples. <br>
```java
@Entity
@XmlRootElement
@Table(name = "DEPARTMENTS")
@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
public class Department extends BaseEntityAUTO implements Transform   {

    //Ok this is a foreign key showing to the same table 
    //so every oranization has a parent organization
    @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    //by using this annotation and implementing Transform interface
    //The Provider will try to convert the id and set organization
    @TransformBean
    private Organization organization;
    
    //and one organization has many child organizations
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Department> childs;
    
    //A one to one relationship
    @OneToOne(mappedBy = "department")
    private DepartmentInfo departmentInfo;

    //Many to Many relationship
    //One dept contains many emps, but also one emp can work one many deps.
    @ManyToMany
    @JoinTable(name = "DEP_EMPS",
            joinColumns=@JoinColumn(name="DEPARTMENT_ID",referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="EMPLOYEE_ID",referencedColumnName="ID"))
    private List<Employee> employees;


    //And an Embedded field
    //embDept contains a simple department description and tha parent department relation.
    @Embedded
    @AttributeOverrides({
    @AttributeOverride(name="description", column=@Column(name="DESCRIPTION")),
    })
    @AssociationOverrides({
      @AssociationOverride(name = "parent",
                  joinColumns = @JoinColumn(name = "PARENT_ID",referencedColumnName = "ID"))
    })
    private EmbeddableDept embDept;
    
    public OrganizationUnit() {
    }

   ...
   setters
   getters
   ...

    //prevent mapping of javabean to xml
    @XmlTransient
    @JsonbTransient
    public List<Employee> getEmployees() {
        return employees;
    }

    //We dont need tha whole organization object
    //Just the id
    @XmlElement(name = "organization")
    @JsonbProperty("organization")
    public Long getOrganizationId() {
        return organization.getId();
    }
}
```
###### Example Ejb service
A simple ejb service for our Department that extending Api abstract class <br>
All the ejb's extending Api<? extends BaseEntity> have the following functionallity
* Master-Detail CRUD Operations
* read range
* validation
* move/transfer
* master-Detail search
* javax.persistence.Embedded support for serialization/desiarization
```java
@Stateless
public class DepartmentFacade extends Api<Department> {
    
    public DepartmentFacade() {
        super(Department.class);
    }
    
    //We are registering the relations and the appropriate functions 
    @Inject
    public DepartmentFacade(DepartmentFacade childService,EmployeeFacade empService,DepartmentInfoFacade infoService) {
        this();
        //one to many
        //one department has many sub departments
        super.addChildDetail(Department.class,Department.class,new OneToManyFunction<Department,Department>() {
                @Override
                public List<Department> getDetails(Department master) {
                    return master.getChilds();
                }

                @Override
                public void setMaster(Department master,Department child) {
                    child.getEmbDept().setParent(master);
                }
        },childService, MoveOption.ORPHANS_ALLOWED);
        
        //many to many
        //one department has many employees
        //but also one employee can work on many departments
        super.addChildDetail(Department.class, Employee.class,new ManyToManyFunction<Department,Employee>() {
                @Override
                public List<Employee> getDetails(Department master) {
                    return master.getEmployees();
                }
                
                @Override
                public void addMaster(Department master, Employee detail) {
                    detail.getDepartments().add(master);
                }
        }, empService, MoveOption.ORPHANS_ALLOWED);
        
        //One To One
        //one Department has One Departnemt Information
        super.addChildDetail(Department.class,DepartmentInfo.class,new OneToOneFunction<Department,DepartmentInfo>(){
            @Override
            public DepartmentInfo getDetail(Department master) {
                return master.getDepartmentInfo();
            }
            
            @Override
            public void setDetail(Department master, DepartmentInfo detail){
                master.setDepartmentInfo(detail);
            }
            
            @Override
            public void setMaster(Department master, DepartmentInfo detail) {
                detail.setDepartment(master);
            }
        }, infoService, MoveOption.ORPHANS_NOT_ALLOWED);
        
    }
}
```

###### Example Jax-Rs endpoint class
A simple jax-rs endpoint for our organization service <br>
every endpoint extending ApiResource have access to the following functionality
* All CRUD by default
* Details CRUD (SubResources) are scoped based on the master
* Search
By extending ApiResource<? extends baseEntity> we have all CRUD operations by default for <br> out entity
```java
@Path("departments")
public class DepartmentResource extends ApiResource<Department> {

    @Inject
    public DepartmentResource(DepartmentFacade service) {
        super(service); 
    }
    
    @Path("{id}/departments")
    public ApiSubResource subResource(@PathParam("id") Long id) {
        return new SubResource(id,getService());
    }
    
    @Path("{id}/employees")
    public ApiSubResource subResource2(@PathParam("id") Long id) {
        return new SubResource2(id,getService());
    }
    
    @Path("{id}/departmentInfo")
    public ApiOneToOneSubResource subResource3(@PathParam("id") Long id) {
        return new SubResource3(id,getService());
    }

    public class SubResource3 extends ApiOneToOneSubResource<Department,DepartmentInfo> {
        public SubResource3(Long id,Api<Department> service) {
            super(id,service,DepartmentInfo.class);
        }
    }
    
    public class SubResource2 extends ApiSubResource<Department,Employee> {
        public SubResource2(Long id,Api<Department> service) {
            super(id,service,Employee.class);
        }
    }
    
    public class SubResource extends ApiSubResource<Department,Department> {
        
        public SubResource(Long id,Api<Department> service) {
            super(id,service,Department.class);
        }
        
        @Path("{id}/departments")
        public ApiSubResource subResource(@PathParam("id") Long id) {
            return new SubResource(id,getService());
        }
    }

}
```
#### Test Run.
<b>And lets see what we can do with this</b>. <br>
<b>GET</b> ```departments/``` -> will return a json list with all department objects.<br>
Create a department.(lets suppose we have an organization with id 1 And a parent department with id 2)<br>
<b>POST</b> Request in ```departments/``` with body
```json
{
    "organization": 1,
    "embDept": {
        "description": "Human R Data And Finan.  SUB_department",
        "parent": 2
    }
}
```
we have the following response if the object is created successfully
```json
{
    "type": "department",
    "id": 1,
	"organization": 1,
    "embDept": {
        "description": "Human R Data And Finan.  SUB_department",
        "parent": 2
    }
}
```
<b>GET</b> ```departments/2/departments``` -> will return list with all Sub departments of department 2.
<br>
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

Now lets Create an Employee and Add him to  the previous department we just created. <br>
If we call <b>GET</b> at ```departments/1/employees ```   -> we get an empty list simple because no employee is working under this one.<br>
So we need to create an Employee update department with working emps and
update employee with working deps<br>
We can do that we a <b>POST</b> at ```departments/1/employees``` <br>
and body data.
```json
{
    "firstName": "John",
    "lastName": "Appleyard"
}
```
a <b>GET</b> at ```departments/1/employees``` 
And we get the a list with the employee with just created.
<br>
Now lets suppose we want a department for the SubDepartment.
We can do that following the same logic.<br>
A <b>POST</b> at ```departments/2/departments/1/departments``` <br>
and body data. without specifying any parent department.<br>
We also specifying the id of the Organization this department belongs
```json
{
    "embDept": {
        "description": "Human R Data  SUB_SUB"
    },
    "organization": 2
}
```
And we get the following response.
```json
{
    "type": "department",
    "id": 77,
    "embDept": {
        "description": "Human R Data  SUB",
        "parent": 1
    },
    "organization": 2
}
```
<b>Move a child Department</b>
Lets move the department with id=3 from parent 2 to parent 1<br>
We simply make a <b>POST</b> at ```departments/1/departments/2/departments/3?moveTo=1```

<b>SEARCH</b> <br>
Not supported yet.
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
