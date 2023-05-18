
import math
import time

class Cache(object):
	L = 16
	K = 0
	N = 0
	size = 0
	offset = 4
	set_bits = 0
	tag_bits = 0
	cache_matrix = []

	def __init__(self, L, N, K):
		self.L = L
		self.N = N
		self.K = K
		self.size = L * K * N 
		self.set_bits = math.log(N, 2)
		self.tag_bits = L - self.offset - self.set_bits
		for i in range(0, N):
			self.cache_matrix.append([bin(i)[2:].zfill(int(self.set_bits)),0])
			for j in range(0, K):
				self.cache_matrix[i].append([0,"",time.time()])

	def print_cache_details(self):
		print("L: " + str(self.L))
		print("N: " + str(self.N))
		print("K: " + str(self.K))
		print("Size: " + str(self.size) + "\n")

	def lru(self, timestamps):
		lru = min(timestamps)
		index = timestamps.index(lru)
		return index

	def disect_address(self, binary):
		binary_minus_offset = binary[:12]
		offset = binary[12:]
		bit_selector_bits = binary_minus_offset[-int(self.set_bits):]
		tag_bits = binary_minus_offset[:-int(self.set_bits)]
		if self.N is 1:
			tag_bits = bit_selector_bits
			bit_selector_bits = "0"
		return bit_selector_bits, tag_bits, offset

	def hex_to_binary(self, hex):
		binary = bin(int(hex, 16))[2:].zfill(16)
		return binary

	def hit_or_miss(self, bit_selector_bits, tag_bits):
		for row in self.cache_matrix:
			if bit_selector_bits == row[0]:
				if self.K is 1:
					if row[2][0] == 0:
						row[2][1] = tag_bits
						row[2][0] = 1
						return("MISS")
					elif row[2][0] == 1:
						if row[2][1] == tag_bits:
							return("HIT")
						else:
							row[2][1] = tag_bits
							return("MISS")

				elif self.N is 1:
					timestamps = []
					for index in range(0, self.K):
						timestamps.append(row[2+index][2])
						if row[2+index][0] == 1:
							if row[2+index][1] == tag_bits:
								row[2+index][2] = time.time()
								return("HIT")

					row[1] = self.lru(timestamps)
					lru = row[1]

					if row[2+lru][0] == 0:
						row[2+lru][1] = tag_bits
						row[2+lru][0] = 1
						row[2+lru][2] = time.time()
						return("MISS")

					elif row[2+lru][0] == 1:
						row[2+lru][1] = tag_bits
						row[2+lru][2] = time.time()
						return("MISS")

				else:
					timestamps = []
					for index in range(0, self.K):
						timestamps.append(row[2+index][2])
						if row[2+index][0] == 1:
							if row[2+index][1] == tag_bits:
								row[2+index][2] = time.time()
								return("HIT")

					row[1] = self.lru(timestamps)
					lru = row[1]

					if row[2+lru][0] == 0:
						row[2+lru][1] = tag_bits
						row[2+lru][0] = 1
						row[2+lru][2] = time.time()
						return("MISS")

					elif row[2+lru][0] == 1:
						row[2+lru][1] = tag_bits
						row[2+lru][2] = time.time()
						return("MISS")
		
addresses = ["0000","0004","000c","2200","00d0","00e0","1130","0028",
		 	 "113c","2204","0010","0020","0004","0040","2208","0008",
			 "00a0","0004","1104","0028","000c","0084","000c","3390",
		 	 "00b0","1100","0028","0064","0070","00d0","0008","3394"]

def run_sim(myCache):
	myCache.print_cache_details()
	hits = 0
	misses = 0
	for hex in addresses:
		binary = myCache.hex_to_binary(hex)
		disected_bits = myCache.disect_address(binary)
		bit_selector_bits = disected_bits[0]
		tag_bits = disected_bits[1]
		hit_or_miss = myCache.hit_or_miss(bit_selector_bits, tag_bits)
		if hit_or_miss == "HIT":
			hits += 1
		else:
			misses += 1
		print("0x" + hex + " " + tag_bits + " " + bit_selector_bits + " " + disected_bits[2] + " was a " + str(hit_or_miss))

	print("HIT COUNT: " + str(hits))
	print("MISS COUNT: " + str(misses))


# uncomment one pair at a time and run

# myCache = Cache(16,8,1)
# run_sim(myCache)

# myCache = Cache(16,4,2)
# run_sim(myCache)

# myCache = Cache(16,2,4)
# run_sim(myCache)

# myCache = Cache(16,1,8)
# run_sim(myCache)