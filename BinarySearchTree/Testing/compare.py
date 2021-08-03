file1 = open(input(), 'r')
file2 = open(input(), 'r')
file1 = file1.readlines()
file2 = file2.readlines()
count = 0
for i in range(0, min(len(file1), len(file2))):
	if file1[i] != file2[i]:
		print(i + 1, ": ", file1[i].rstrip(), file2[i].rstrip())
		count += 1
	if count == 5:
		break
print(len(file1), len(file2), count)
