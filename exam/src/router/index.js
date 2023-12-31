import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login', //登录界面
      component: () => import('@/components/common/login')
    },
    {
      path: '/wqset',
      component: () => import('@/components/student/wqset')
    },
    {
      path: '/register',
      component: () => import('@/components/common/register')
    },
    {
      path: '/video1',
      component: () => import('@/components/common/video1')
    },
    {
      path: '/test',
      component: () => import('@/components/common/test')
    },
    {
      path: '/index', //教师主页
      component: () => import('@/components/admin/index'),
      children: [
        {
          path: '/', //首页默认路由
          component: () => import('@/components/common/hello')
        },
        {
          path:'/grade', //学生成绩
          component: () => import('@/components/charts/grade')
        },
        {
          path:'/grade1', //学生成绩
          component: () => import('@/components/charts/grade1')
        },
        {
          path:'/grade2', //学生成绩
          component: () => import('@/components/charts/grade2')
        },
        {
          path: '/selectExamToPart', //学生分数段
          component: () => import('@/components/teacher/selectExamToPart')
        },
        {
          path: '/scorePart',
          component: () => import('@/components/charts/scorePart')
        },
        {
          path: '/allStudentsGrade', //所有学生成绩统计
          component: () => import('@/components/teacher/allStudentsGrade')
        },
        {
          path: '/selectExam', //查询所有考试
          component: () => import('@/components/teacher/selectExam')
        },
        {
          path: '/addExam', //添加考试
          component: () => import('@/components/teacher/addExam')
        },
        {
          path: '/addVideo', //添加视频
          component: () => import('@/components/teacher/addVideo')
        },
        {
          path: '/selectAnswer', //查询所有题库
          component: () => import('@/components/teacher/selectAnswer')
        },
        {
          path: '/addAnswer', //增加题库主界面
          component: () => import('@/components/teacher/addAnswer')
        },
        {
          path: '/addAnswerChildren', //点击试卷跳转到添加题库页面
          component: () => import('@/components/teacher/addAnswerChildren')
        },
        {
          path: '/studentManage', //学生管理界面
          component: () => import('@/components/teacher/studentManage')
        },
        {
          path: '/videoManage', //视频管理界面
          component: () => import('@/components/teacher/videoManage')
        },
        {
          path: '/mpwd', //修改密码
          component: () => import('@/components/teacher/mpwd')
        },
        {
          path: '/addStudent', //添加学生
          component: () => import('@/components/teacher/addStudent')
        },
        {
          path: '/teacherManage',
          component: () => import('@/components/admin/teacherManage')
        },
        {
          path: '/addTeacher',
          component: () => import ('@/components/admin/addTeacher')
        }
      ]
    },
    {
      path: '/student',
      component: () => import('@/components/student/index'),
      children: [
        {path:"/",component: ()=> import('@/components/student/myExam')},
        {path:'/startExam', component: () => import('@/components/student/startExam')},
        {path: '/manager', component: () => import('@/components/student/manager')},
        {path: '/examMsg', component: () => import('@/components/student/examMsg')},
        {path: '/message', component: () => import('@/components/student/message')},
        {path: '/studentScore', component: () => import("@/components/student/answerScore")},
        {path: '/scoreTable', component: () => import("@/components/student/scoreTable")},
        {path: '/messageshow', component: ()=> import("@/components/student/messageshow")},
        {path: '/video', component: () => import('@/components/student/video')},
        {
          path:'/grade3', //学生成绩
          component: () => import('@/components/charts/grade3')
        },
      ]
    },
    {path: '/answer',component: () => import('@/components/student/answer')}
  ]
})
