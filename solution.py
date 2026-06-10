import sys

import gc
# Enable GC debugging to track unreachable objects
gc.set_debug(gc.DEBUG_SAVEALL)
class CircularNode:
def __init__(self, label):
self.label = label
self.next = None # Will hold a reference to another node
def __del__(self):
print(f"CircularNode '{self.label}' is being garbage-collected")
print("=== Initialising Nodes ===")
# Step 1: Create two node instances
node1 = CircularNode("X")
node2 = CircularNode("Y")
# Step 2: Form a reference cycle X -> Y -> X
node1.next = node2
node2.next = node1
print("Reference cycle established:")
print(f" node1.next --> {node1.next.label}")
print(f" node2.next --> {node2.next.label}")
print("\n=== Reference Counts Before Deletion ===")
# Note: sys.getrefcount() itself adds one transient reference
print(f" ref-count of node1: {sys.getrefcount(node1)}")
print(f" ref-count of node2: {sys.getrefcount(node2)}")
print("\n=== Removing External References ===")
id1 = id(node1)
id2 = id(node2)
# Step 3: Drop the only external handles
del node1
del node2
print(" Variables node1 and node2 have been deleted.")
print("\n=== Probing the GC Object List ===")
# Objects are still alive because the cycle keeps ref-counts > 0
alive1 = any(id(o) == id1 for o in gc.get_objects())
alive2 = any(id(o) == id2 for o in gc.get_objects())
print(f" node1 still in memory? {alive1}")

print(f" node2 still in memory? {alive2}")
print("\n=== Forcing Garbage Collection ===")
# Step 4: Trigger the cyclic GC
collected = gc.collect()
print(f" Unreachable objects reclaimed: {collected}")
print("\n=== Post-Collection Check ===")
alive1 = any(id(o) == id1 for o in gc.get_objects())
alive2 = any(id(o) == id2 for o in gc.get_objects())
print(f" node1 still in memory? {alive1}")
print(f" node2 still in memory? {alive2}")
print("\n=== gc.garbage Inspection ===")
print(f" Items remaining in gc.garbage list: {len(gc.garbage)}")
