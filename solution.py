class Address:
    def __init__(self, street, city, zipCode):
        self.street = street
        self.city = city
        self.zipCode = zipCode


    def display(self):
        print(f"{self.street}, {self.city} - {self.zipCode}")


class Student:
    def __init__(self, name, age, address):
        self.name = name
        self._age = None  # protected attribute
        self.age = age    # using setter validation
        self.address = address  # HAS-A relationship (composition)
        self.courses = []  # mutable list


    # getter
    @property
    def age(self):
        return self._age


    # setter with validation
    @age.setter
    def age(self, value):
        if value < 5 or value > 100:
            raise ValueError("Age must be between 5 and 100")
        self._age = value


    # add course
    def add_course(self, course):
        self.courses.append(course)


    # display method
    def display(self):
        print("\n--- Student Details ---")
        print("Name:", self.name)
        print("Age:", self.age)
        print("Courses:", self.courses)
        print("Address:", end=" ")
        self.address.display()


class ScholarshipStudent(Student):
    def __init__(self, name, age, address, scholarshipAmount):
        super().__init__(name, age, address)
        self.scholarshipAmount = scholarshipAmount


    # override display
    def display(self):
        super().display()
        print("Scholarship Amount:", self.scholarshipAmount)


addr1 = Address("MG Road", "Guwahati", "781001")


s1 = Student("Amit", 20, addr1)
s1.add_course("Math")
s1.add_course("Physics")


s2 = ScholarshipStudent("Riya", 22, addr1, 50000)
s2.add_course("Biology")


students = [s1, s2]


for s in students:
    s.display()
