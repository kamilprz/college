from unittest import TestCase

from dag import *


class TestDAG(TestCase):

    # test for add empty node - TypeError
    def test_addNode_Empty(self):
        dag = DAG()
        self.assertRaises(TypeError, lambda: dag.add_node())

    # test for adding one node
    def test_addNode(self):
        dag = DAG()
        dag.add_node('A')
        self.assertTrue(dag.graph == {'A': []})

    # test for a non-existent node
    def test_addNode_NonExistent(self):
        dag = DAG()
        dag.add_node('B')
        self.assertFalse(dag.graph == {'A': []})

    # test for duplicate of same node - returns False if node already in graph
    def test_addNode_Duplicate(self):
        dag = DAG()
        dag.add_node('A')
        self.assertFalse(dag.add_node('A'))

    # test add edge
    def test_addEdge(self):
        # A -> B
        dag = DAG()
        dag.add_node('A')
        dag.add_node('B')
        self.assertTrue(dag.add_edge('A', 'B') is True)

    # test add edge with 1 non existing node - KeyError
    def test_addEdge_OneMissing(self):
        dag = DAG()
        dag.add_node('A')
        self.assertRaises(KeyError, lambda: dag.add_edge('A', 'B'))

    # test add edge with 2 non existing nodes - KeyError
    def test_addEdge_TwoMissing(self):
        dag = DAG()
        self.assertRaises(KeyError, lambda: dag.add_edge('A', 'B'))

    # test for isAcyclic - returns false if the graph contains a cycle
    def test_isAcyclic_No(self):
        dag = DAG()
        dag.add_node('B')
        dag.add_node('C')
        dag.add_edge('B', 'C')
        dag.add_node('D')
        dag.add_edge('C', 'D')
        dag.add_edge('D', 'B')

        #        B
        #      /   \
        #     C  -  D

        self.assertFalse(isAcyclic_wrapper(dag.graph))

    # graph contains no cycles
    def test_isAcyclic_Yes(self):
        dag = DAG()
        dag.add_node('A')
        dag.add_node('B')
        dag.add_node('C')
        dag.add_node('D')
        dag.add_edge('B', 'C')
        dag.add_edge('B', 'A')
        dag.add_edge('C', 'D')

        #       B
        #     /   \
        #    C     A
        #   /
        #  D

        self.assertTrue(isAcyclic_wrapper(dag.graph))

    # test for LCA with empty graph
    def test_findLCA_Empty(self):
        dag = DAG()
        self.assertTrue(findLCA(dag.graph, 'A', 'B') is -1)

    # test for LCA with one node in graph
    def test_findLCA_OneNode(self):
        dag = DAG()
        dag.add_node('A')
        self.assertTrue(findLCA(dag.graph, 'A', 'B') is -1)

    # test for LCA with two nodes and an edge in the graph
    def test_findLCA_OneNotInGraph(self):
        dag = DAG()
        dag.add_node('A')
        dag.add_node('B')
        dag.add_edge('A', 'B')
        self.assertTrue(findLCA(dag.graph, 'A', 'G') is -1)

    # test for LCA with itself
    def test_findLCA_WithItself(self):
        dag = DAG()
        dag.add_node('A')
        self.assertEqual(findLCA(dag.graph, 'A', 'A'), 'A')

    # test for LCA with both nodes in graph but no edge between them
    def test_findLCA_NoEdge(self):
        dag = DAG()
        dag.add_node('A')
        dag.add_node('B')
        self.assertEqual(findLCA(dag.graph, 'A', 'B'), -1)

    # test for LCA with 2 nodes - one of which is the LCA
    def test_findLCA_OneNodeIsLCA(self):
        dag = DAG()
        dag.add_node('A')
        dag.add_node('B')
        dag.add_edge('A', 'B')
        self.assertEqual(findLCA(dag.graph, 'A', 'B'), 'A')

    # test for LCA with 2 nodes both sides of root
    def test_findLCA_BothSides(self):
        dag = DAG()
        dag.add_node('A')
        dag.add_node('B')
        dag.add_node('C')
        dag.add_edge('A', 'B')
        dag.add_edge('A', 'C')

        #         A
        #        / \
        #       B   C

        self.assertEqual(findLCA(dag.graph, 'B', 'C'), 'A')

    # test for findLCA with a cyclic graph
    def test_findLCA_Cyclic(self):
        dag = DAG()
        dag.add_node('B')
        dag.add_node('C')
        dag.add_edge('B', 'C')
        dag.add_node('D')
        dag.add_edge('C', 'D')
        dag.add_edge('D', 'B')

        #        B
        #      /   \
        #     C  -  D

        self.assertEqual(findLCA(dag.graph, 'B', 'C'), -1)

    # test for LCA with a bigger graph
    def test_findLCA_BigGraph(self):
        dag = DAG()
        dag.add_node('A')
        dag.add_node('B')
        dag.add_node('C')
        dag.add_node('D')
        dag.add_node('E')
        dag.add_node('F')
        dag.add_node('G')
        dag.add_node('H')
        dag.add_node('I')
        dag.add_node('J')

        dag.add_edge('A', 'B')
        dag.add_edge('A', 'C')
        dag.add_edge('B', 'D')
        dag.add_edge('B', 'E')
        dag.add_edge('B', 'F')
        dag.add_edge('C', 'G')
        dag.add_edge('C', 'H')
        dag.add_edge('F', 'I')
        dag.add_edge('F', 'J')

        #             A
        #            /  \
        #          /      \
        #         B        C
        #       / | \     /  \
        #      D  E  F   G    H
        #            |
        #           / \
        #          I   J

        self.assertEqual(findLCA(dag.graph, 'E', 'I'), 'B')
        self.assertEqual(findLCA(dag.graph, 'E', 'H'), 'A')
        self.assertEqual(findLCA(dag.graph, 'A', 'J'), 'A')
        self.assertEqual(findLCA(dag.graph, 'G', 'H'), 'C')
        self.assertEqual(findLCA(dag.graph, 'I', 'J'), 'F')

    # test for line graph
    def test_findLCA_LineGraph(self):
        dag = DAG()
        dag.add_node('A')
        dag.add_node('B')
        dag.add_node('C')
        dag.add_node('D')
        dag.add_node('E')
        dag.add_node('F')

        dag.add_edge('A', 'B')
        dag.add_edge('B', 'C')
        dag.add_edge('C', 'D')
        dag.add_edge('D', 'E')
        dag.add_edge('E', 'F')

        #   A
        #    \
        #     B
        #      \
        #       C
        #        \
        #         D
        #          \
        #           E
        #            \
        #             F

        self.assertEqual(findLCA(dag.graph, 'B', 'C'), 'B')
        self.assertEqual(findLCA(dag.graph, 'A', 'F'), 'A')

    # test for graph from lecture slides
    def test_findLCA_LectureGraph(self):
        dag = DAG()
        dag.add_node(1)
        dag.add_node(2)
        dag.add_node(3)
        dag.add_node(4)
        dag.add_node(5)
        dag.add_node(6)
        dag.add_node(7)
        dag.add_node(8)
        dag.add_node(9)
        dag.add_node(10)
        dag.add_node(11)
        dag.add_node(12)
        dag.add_node(13)

        dag.add_edge(1, 2)
        dag.add_edge(2, 4)
        dag.add_edge(4, 6)
        dag.add_edge(1, 3)
        dag.add_edge(3, 5)
        dag.add_edge(5, 8)
        dag.add_edge(5, 7)
        dag.add_edge(7, 10)
        dag.add_edge(10, 9)
        dag.add_edge(10, 13)
        dag.add_edge(10, 11)
        dag.add_edge(11, 12)

        #              1
        #             / \
        #            2   3
        #           /   /
        #          4   5
        #         /   / \
        #        6   7   8
        #            |
        #          / | \
        #         9  13  11
        #                  \
        #                   12

        self.assertEqual(findLCA(dag.graph, 8, 9), 5)
        self.assertEqual(findLCA(dag.graph, 11, 6), 1)
        self.assertEqual(findLCA(dag.graph, 3, 12), 3)