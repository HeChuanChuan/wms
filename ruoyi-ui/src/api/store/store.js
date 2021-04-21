import request from '@/utils/request'

//查询仓库列表
export function listStore(query) {
  return request({
    url: '/wms/store/list',
    method: 'get',
    params: query
  })
}

//新增仓库
export function addStore(data) {
  return request({
    url: '/wms/store',
    method: 'post',
    data: data
  })
}

//修改仓库
export function updateStore(data) {
  return request({
    url: '/wms/store',
    method: 'put',
    data: data
  })
}

//仓库状态修改
export function changeStoreStatus(id, storeStatus) {
  const data = {
    id,
    storeStatus
  }
  return request({
    url: '/wms/store/changeStatus',
    method: 'put',
    data: data
  })
}

//删除仓库
export function delStore(id) {
  return request({
    url: '/wms/store/' + id,
    method: 'delete'
  })
}

//导出仓库信息
export function exportStore(query) {
  return request({
    url: '/wms/store/export',
    method: 'get',
    params: query
  })
}

//获取仓库选择框列表
export function optionselect() {
  return request({
    url: '/wms/store/optionselect',
    method: 'get',
  })
}
