Dict1={}
List1={}
List2={}
for i in range(0,10):
    products=input("enter the product name\n")
    List1[i]=products


    Qty=int(input("Enter the quantity\n"))
    List2[i]=Qty


    Dict1[List1[i]]=List2[i]
print("\n")
for i, j in Dict1.items():
    if(j<10):
        print(i,"\n")



