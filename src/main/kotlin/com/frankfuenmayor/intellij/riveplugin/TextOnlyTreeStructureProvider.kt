package com.frankfuenmayor.intellij.riveplugin

import com.intellij.ide.projectView.TreeStructureProvider
import com.intellij.ide.projectView.ViewSettings
import com.intellij.ide.projectView.impl.nodes.PsiFileNode
import com.intellij.ide.util.treeView.AbstractTreeNode
import org.jetbrains.annotations.NotNull


internal class TextOnlyTreeStructureProvider : TreeStructureProvider {
    @NotNull
    override fun modify(
        @NotNull parent: AbstractTreeNode<*>,
        @NotNull children: Collection<AbstractTreeNode<*>>,
        settings: ViewSettings
    ): Collection<AbstractTreeNode<*>> {

        val nodes = ArrayList<AbstractTreeNode<*>>()

        for (child in children) {
            if (child is PsiFileNode) {
                val file = child.virtualFile
                if (file != null && !file.isDirectory && file.fileType !is RiveFileType) {
                    continue
                }
            }
            nodes.add(child)
        }

        return nodes
    }
}