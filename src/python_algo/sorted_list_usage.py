from sortedcontainers import SortedList

# Not supported的情况：改变某个 index 的值，改变顺序
# Initialize a sorted list
sl = SortedList([1, 2, 3, 4, 5, 5, 5, 6, 7, 7, 8, 9, 10])
# add
sl.add(7)
# 删除某个值
sl.remove(7)
# 删除某个下标的值
del sl[0]
# pop
sl.pop()
# check if sl contains 9
print(9 in sl)
# count
sl.count(5)
# index
sl.index(5)
# get by index
print(sl[0])
# bisect_left，获取最左 7 的下标
sl.bisect_left(7)
# bisect_right，获取最右 7 的下标+1，方便切片
sl.bisect_right(7)
# slice
print(sl[sl.bisect_left(7): sl.bisect_right(7)])
