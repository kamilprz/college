class Node:
    def __init__(self, key=None):
        self.key = key
        self.left = None
        self.right = None


def findPath(root, path, x):
    if root.key is None:
        return False

    path.append(root.key)
    if root.key == x:
        return True

    if ((root.left is not None and findPath(root.left, path, x)) or
            (root.right is not None and findPath(root.right, path, x))):
        return True

    path.pop()
    return False


def findLCA(root, n1, n2):
    path1 = []
    path2 = []

    if not findPath(root, path1, n1) or not findPath(root, path2, n2):
        return -1

    i = 0
    while i < len(path1) and i < len(path2):
        if path1[i] != path2[i]:
            break
        i += 1
    return path1[i - 1]