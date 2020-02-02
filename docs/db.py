# -*- coding: utf-8 -*-
import random, string
from datetime import date
first=[]
last=[]
street_list=[]
postal_code_list=[]
city_list=[]
temp=[]
def year_():
	return random.randrange(2011,2018)
def day():
	return random.randrange(1,28)
def month():
	return random.randrange(1,12)
def hour():
	return random.randrange(1,24)
def minute():
	return random.randrange(0,59)
def second():
	return random.randrange(0,59)
def date_(year= 0):
	if year==0:year= year_()
	return "{:02d}–{:02d}–{:02d}".format(year, month(), day())
def daytime():
	return "{:02d}–{:02d}–{:02d} {:02d}:{:02d}:{:02d}".format(year_(), month(), day(), hour(),minute(),second())
def date_sorted(year=0):
	if year== 0:year= year_()
	date_list=[date.fromordinal(730920).replace(year, month(), day()) for temp in range( 0,4)]
	date_list.sort()
	return date_list
def randomword(length):
   return ''.join([random.choice(string.hexdigits+string.ascii_uppercase) for i in range(length)])

with open("first names.txt","r") as file:
	for line in file:
		first.append(line.replace("\n", "").replace(" \xa0",""))
	first=[temp for temp in first if temp!=""]
	print(first)


with open("last names.txt","r") as file:
	for line in file:
		last.append(line.replace("\n", "").lower().capitalize())
	last=[temp for temp in last if temp!=""]
	#print(last)


with open("streets.txt","r") as file:
	for line in file:
		street_list.append(line.replace("\n", ""))
	street_list=[temp for temp in street_list if temp!=""]
	#print(street_list)


with open("postal codes.txt","r") as file:
	for line in file:
		temp.append(line.replace("\n", ""))
	temp=[temp_ for temp_ in temp if temp_!=""]
	

for counter in range(0,len(temp)):
	if counter% 2== 0:
		postal_code_list.append(temp[counter])
	else:
		city_list.append(temp[counter])


def first_name():
	return random.choice(first)
def last_name():
	return random.choice(last)
def street():
	return random.choice(street_list)
def postal_code():
	return random.choice(postal_code_list)
def Street_number():
	return random.randrange(1,300)

def city():
	return random.choice(city_list).replace(",", "")
def IRS_number_Generator():
	unique={}
	number=random.randrange(1000000000,9999999999)
	while(True):
		while( number in unique):
			number=random.randrange(1000000000,9999999999)
			unique[number]=0

		yield number


		return random.randrange(1000000000,9999999999)
def driver_license_Generator():
	unique={}
	number= random.randrange(111111111111,999999999999)
	while(True):
		while( number in unique):
			number= random.randrange(111111111111,999999999999)


		yield number

def Social_Security_number_Generator():
	unique={}
	number=random.randrange(100000000,999999999)
	while(True):
		while( number in unique):
			number=random.randrange(100000000,999999999)
			unique[number]=0

		yield number

def Social_Security_number():
	return next(Social_Security_number_Generator())
def driver_license():
	return next(driver_license_Generator())
def IRS_number():
	return next(IRS_number_Generator())






with open("employee.csv","w") as file:
 	IRS=[]
 	for counter_ in range(0, 100):
 		number=IRS_number()
 		IRS.append(number)
 		file.write("'{}','{}','{}','{}','{}','{}','{}','{}','{}'\n".format(number, Social_Security_number(),driver_license(), first_name(), last_name(),street(),Street_number(), postal_code(), city()))
		
with open("customers.csv","w") as file:
 	customers=[]
 	for counter_ in range(1, 30):
 		number=IRS_number()
 		customers.append(counter_)

 		file.write("'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}'\n".format(counter_,number, Social_Security_number(), first_name(), last_name(),driver_license(),date_sorted()[0],city(),postal_code(), street(), Street_number()))
 		print(Street_number())
		
position_list=["clerk","receptionist","receptionist","receptionist","receptionist","receptionist","accountant","store manager","unassigned","technical personnel","car mechanic","PR","cleaner","technical inspector"]
def position():
	return random.choice(position_list)


works={}
with open("works.csv","w") as file:
	for worker in IRS:

		dates=date_sorted(2018)
		works[worker]=random.randrange(3000, 3010)


		file.write("'{}','{}','{}','{}','{}'\n".format(worker,works[worker],dates[0], dates[3],position()) )


	
type_list={1:"car",2:"motorcycle",3:"ATV",4:"mini van",5:"truck"}
brand_list=["Audi","BMW","Nissan","Subaru","Toyota","Mercedes-Benz","Kia","Alfa Romeo","Tesla"]
model_list=[randomword(3).upper() + str(random.randrange(0, 999))  for counter in range( 0,300 )]
damages_list=["none","none","none","none","none","none","none","none","none","none","none","none","none","none","none","light scratches","slight bumps","flickering headlight","worn-out seats","out of service","heavily damaged"]
malfunction_list=["none","none","none","none","none","none","none","none","none","none","unresponsive brakes","malfunctioning electrical windows","multimedia center out of order","fluid leak"]

def type_():
	return random.choice(list(type_list.keys()))
def make():
	return random.choice(brand_list)
def model():
	return random.choice(model_list)
def license_plate():
	return "{}{}".format(randomword(3).upper(), random.randrange(210, 999))
def kilometers():
	return random.randrange(0, 200000)
def cylinders():
	return random.randrange(1000, 10000)
def horsepower():
	return random.randrange(44, 1000)
def damages():
	return random.choice(damages_list)
def malfunctions():
	return random.choice(malfunction_list)



with open("vehicle_type.csv","w") as Vehicle_file_:
	for counter in range( 1, 6):
		Vehicle_file_.write("'{}','{}'\n".format(counter, type_list[counter] ) )




vehicles={}
with open("vehicle.csv","w") as vehicle:
	license_plates=[]
	for counter_ in range(3000, 3030):
		dates=date_sorted()
		lic=license_plate()
		license_plates.append(lic)
		vehicles[lic]=random.randrange(3000, 3010 )
		vehicle.write("'{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}','{}'\n".format(lic, model(),type_(), make(), dates[0].year, kilometers(),cylinders(), horsepower(), damages(), malfunctions(),\
			dates[2], dates[3],dates[1],vehicles[lic]))

fuel=["gasoline","gas","diesel","electrical"]
with open("fuel_type.csv","w") as file:
	for counter in license_plates:
		file.write("'{}','{}'\n".format(counter, random.choice(fuel)))

stores={}
with open("stores.csv","w") as file:
	for counter in range(0, 3011):

		stores[counter]=city()
		file.write("'{}','{}','{}','{}','{}'\n".format(counter, street(), Street_number(),postal_code(),stores[counter] ))

def return_state():
	states=["perfect","slight damages","heavily damaged"]
	return random.choice(states)

payment_methods=["Credit card","cash","Western Union","Cryptocurrency","manual labor"]
with open("rents.csv","w") as file:
	with open("payments.csv","w"): pass
	unsafe_dates={}
	license_plates_= license_plates[:]

	for license  in license_plates:
		unsafe_dates[license]=[]
	for customer in customers:
		
		
		license=random.choice(license_plates)
		dates=date_sorted(2018)
		unsafe_dates[license].append((dates[0], dates[3]))
		license_plates.remove(license )
		corresponding_store_city=stores[vehicles[license]]
		employees=[IRS_number_ for IRS_number_ in IRS if works[IRS_number_]== vehicles[license]]
		employee_choice= random.choice(employees )
		with open("payments.csv","a") as payment_file:
			payment_file.write("'{}','{}','{}','{}'\n".format(dates[0], license, random.randrange(20, 100),random.choice(payment_methods)))

		

		file.write("'{}','{}','{}','{}','{}','{}','{}','{}'\n".format(license, dates[0],corresponding_store_city,stores[random.randrange(3000, 3010 )], dates[3],return_state(),customer,employee_choice) )
	license_plates= license_plates_


def safe(date,date_span):
	for dangers in date_span:
		danger_begin, danger_finish= dangers
		if (date >= danger_begin and date<= danger_finish):
			return False
	return True





with open("reserves.csv","w") as file:
	license_plates_= license_plates[:]
	for customer in customers:
		
		
		dates=date_sorted(2018)
		
		license=random.choice(license_plates)
		while(not safe(dates[0], unsafe_dates[license]) and not safe(dates[3], unsafe_dates[license])):dates=date_sorted(2018)
		license_plates.remove(license )
		corresponding_store_city=stores[vehicles[license]]
		employees=[IRS_number_ for IRS_number_ in IRS if works[IRS_number_]== vehicles[license]]
		employee_choice= random.choice(employees )
		paid= random.randrange(0,1)


		

		file.write("'{}','{}','{}','{}','{}','{}'\n".format(license, dates[0],corresponding_store_city,stores[random.randrange(3000, 3010 )], dates[3],customer) )
	print(customers)

	license_plates= license_plates_

