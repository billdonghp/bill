# bill
https://blog.csdn.net/u013374164/article/details/78831480

那么首先打开git bash
输入git init
然后git add dns4test.c
之后 git commit -m "描述"         （这里的描述就填一些你对这个文件的说明就好啦）
之后  git remote add origin https://github.com/huijuanl/huijuan072.git    （这是我的远程仓库url）
然后git push -u origin master
但是这里可能会报错:To https://github.com/huijuanl/huijuan0723.git
 ! [rejected]        master -> master (non-fast-forward)
error: failed to push some refs to 'https://github.com/huijuanl/huijuan072.git'
hint: Updates were rejected because the tip of your current branch is behind
hint: its remote counterpart. Integrate the remote changes (e.g.
hint: 'git pull ...') before pushing again.
hint: See the 'Note about fast-forwards' in 'git push --help' for details.
这是因为推送代码前需要先从服务器pull最新的代码。
继续输入命令: git pull origin master--allow-unrelated-histories
这里是允许合并了远程的分支，会跳入vim编辑器，只要按住esc键，同时按两下大写的z就可以退出
继续输入git push -u origin master就成功了。

git init 初始化一个 git 仓库
1.1 进入需要使用git 管理的文件初始化一个仓库 git init
git add   提交代码到暂存区
1.1 使用add 提交代码到暂存区 git add[<file>]

git commit 
 提交代码到版本库
1.1 将编辑好的代码提交到版本库 git commit  [<file>] -m "备注信息"
1.2 更改最近一次提交的备注信息 git commit --amend [<file>] -m "新的备注信息"
1.3 查看提交的详细修改内容 git commit --verbose 

git status 查看当前工作目录中文件状态
1.1 查看工作目录中文件状态 git status [<file>]

