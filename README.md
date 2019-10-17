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
