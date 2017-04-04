

/**
  * Created by louie on 4/3/2017.
  */
package sparksql.model

case class Employee(EmployeeID : String,
                    LastName : String,
                    FirstName : String,
                    Title : String,
                    BirthDate : String,
                    HireDate : String,
                    City : String,
                    State : String,
                    Zip : String,
                    Country : String,
                    ReportsTo : String)

case class Order(OrderID : String,
                 CustomerID : String,
                 EmployeeID : String,
                 OrderDate : String,
                 ShipCountry : String)

case class OrderDetails(OrderID : String,
                        ProductID : String,
                        UnitPrice : Double,
                        Qty : Int,
                        Discount : Double)