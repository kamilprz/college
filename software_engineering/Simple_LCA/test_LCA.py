from unittest import TestCase

from lca import *


class TestLCA(TestCase):
    # tests for checking if Node initialisation works

    # null
    def test_nodeInitEmpty(self):
        node = Node()
        self.assertEqual(node.key, None)

    # key and comparison equal
    def test_nodeInitTrue(self):
        node = Node(6)
        self.assertEqual(node.key, 6, True)

    # key and comparison not equal
    def test_nodeInitFalse(self):
        node = Node(6)
        self.assertFalse(node.key == 8)

    # tests for findPath - the function returns a bool whether it's possible to find a path to said node

    # test when the tree is empty
    def test_findPathEmpty(self):
        root = Node()
        path = []
        self.assertEqual(findPath(root, path, 6), False)

    # test for node not which isn't root when only root in tree
    def test_findPathNotInTree(self):
        path = []
        root = Node(1)
        self.assertEqual(findPath(root, path, 5), False)

    # find path from root to itself
    def test_findPathRoot(self):
        path = []
        root = Node(1)
        self.assertEqual(findPath(root, path, 1), True)

    # test when the tree is non-empty
    def test_findPath(self):
        path = []
        root = Node(1)
        root.left = Node(2)
        root.right = Node(3)
        root.left.left = Node(4)
        root.left.right = Node(5)
        root.right.left = Node(6)
        root.right.right = Node(7)

        #            1
        #         /     \
        #        2       3
        #       / \     / \
        #      4   5   6   7

        # root in complete tree
        self.assertEqual(findPath(root, path, 1), True)

        # middle node
        self.assertEqual(findPath(root, path, 3), True)

        # node at bottom of tree
        self.assertEqual(findPath(root, path, 6), True)

        # not in tree
        self.assertEqual(findPath(root, path, 10), False)

    # tests for the findLCA function - returns LCA if one exists, if not, returns -1

    # test for empty tree
    def test_findLCAEmpty(self):
        root = Node()
        path = []
        self.assertEqual(findLCA(root, 1, 2), -1)

    def test_findLCANotInTree(self):
        root = Node(1)
        # node not in tree
        self.assertEqual(findLCA(root, 1, 5), -1)

    # test for only root - a node is defined to be an ancestor of itself
    def test_findLCARoot(self):
        root = Node(1)
        path = []
        self.assertEqual(findLCA(root, 1, 1), 1)

    # test for complete tree
    def test_findLCA(self):
        root = Node(1)
        root.left = Node(2)
        root.right = Node(6)
        root.left.left = Node(3)
        root.left.left.right = Node(4)
        root.left.left.right.right = Node(5)
        root.left.right = Node(9)
        root.right.right = Node(7)
        root.right.right.right = Node(8)

        #           1
        #          / \
        #         2   6
        #        / \   \
        #       3   9   7
        #        \       \
        #         4       8
        #          \
        #           5

        # nodes on both sides of tree
        self.assertEqual(findLCA(root, 8, 5), 1)

        # nodes on same side of root, different children
        self.assertEqual(findLCA(root, 4, 9), 2)

        # same side of root, one of the nodes is the LCA
        self.assertEqual(findLCA(root, 6, 7), 6)