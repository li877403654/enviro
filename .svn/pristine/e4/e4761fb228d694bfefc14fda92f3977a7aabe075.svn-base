package pub.models.tree

/**
 * Created by zzl.
 * Date: 2018-05-06
 */
class SimpleTreeModel : TreeModel {

    override var rootNode = SimpleTreeNode()

    fun <T> fillBeans(beans: List<T>,
                      idGetter: (T) -> Int?,
                      nameGetter: (T) -> String?,
                      parentIdGetter: (T) -> Int?,
                      sortByName: Boolean = false,
                      setData: Boolean = true) {

        rootNode.id = 0
        rootNode.name = "ROOT"
        rootNode.childNodes = mutableListOf()

        val nodeMap = mutableMapOf<Int, SimpleTreeNode>()
        nodeMap[0] = rootNode

        for (bean in beans) {
            val id = idGetter(bean)!!
            val name = nameGetter(bean)!!
            //val parentId = parentIdGetter(bean)
            val node = SimpleTreeNode()
            node.id = id
            node.name = name
            if(setData) {
                node.data = bean
            }
            nodeMap[id] = node
        }
        for (bean in beans) {
            val id = idGetter(bean)!!
            val node = nodeMap[id]!!

            val parentId = parentIdGetter(bean)?: 0
            val parentNode = nodeMap[parentId]!!
            var childNodes = parentNode.childNodes
            if(childNodes == null) {
                childNodes = mutableListOf()
                parentNode.childNodes = childNodes
            }
            childNodes.add(node)
        }
        if(sortByName) {
            sortChildNodes(rootNode.childNodes!!)
        }
    }

    fun findNode(id: Int): SimpleTreeNode {
        return _findNode(rootNode, id)!!
    }

    private fun _findNode(parentNode: SimpleTreeNode, id: Int): SimpleTreeNode? {
        if(parentNode.id == id) {
            return parentNode
        }
        val childNodes = parentNode.childNodes
        if(childNodes == null) {
            return null
        }
        for(childNode in childNodes) {
            val node = _findNode(childNode, id)
            if(node != null) {
                return node
            }
        }
        return null
    }

    private fun sortChildNodes(childNodes: MutableList<SimpleTreeNode>) {
        childNodes.sortBy { it.name }
        for(childNode in childNodes) {
            val grandChildNodes = childNode.childNodes
            if(grandChildNodes != null) {
                sortChildNodes(grandChildNodes)
            }
        }
    }


}