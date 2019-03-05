export default [
  {
    path: '/manager/${vueModuleNameLowerCamel}List',
    name: '${vueModuleNameLowerCamel}List',
    meta: {
      label: '${vueModuleChineseName}管理',
      requireAuth: true,
      permission: "${vueModuleNameLowerCamel}_left"
    },
    component: () => import('./${vueModuleNameLowerCamel}List'),
  },
  {
    path: '/manager/${vueModuleNameLowerCamel}Form/add',
    name: '${vueModuleNameLowerCamel}Form_Add',
    meta: {
      label: '新增${vueModuleChineseName}',
      requireAuth: true,
      permission: '${vueModuleNameLowerCamel}_add'
    },
    component: () => import('./${vueModuleNameLowerCamel}Form')
  },
  {
    path: '/manager/${vueModuleNameLowerCamel}Form/edit/:${vueModuleNameLowerCamel}Id',
    name: '${vueModuleNameLowerCamel}Form_Edit',
    meta: {
      label: '编辑${vueModuleChineseName}',
      requireAuth: true,
      permission: '${vueModuleNameLowerCamel}_edit'
    },
    component: () => import('./${vueModuleNameLowerCamel}Form')
  },
  {
    path: '/manager/${vueModuleNameLowerCamel}Display/:${vueModuleNameLowerCamel}Id',
    name: '${vueModuleNameLowerCamel}Display',
    meta: {
      label: '查看${vueModuleChineseName}',
      requireAuth: true,
      permission: '${vueModuleNameLowerCamel}_display'
    },
    component: () => import('./${vueModuleNameLowerCamel}Display')
  }
]
