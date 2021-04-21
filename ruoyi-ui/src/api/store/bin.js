import request from '@/utils/request'

//查询仓库列表
export function listBin(query) {
  return request({
    url: '/wms/bin/list',
    method: 'get',
    params: query
  })
}

//新增仓库
export function addBin(data) {
  return request({
    url: '/wms/bin',
    method: 'post',
    data: data
  })
}

//修改仓库
export function updateBin(data) {
  return request({
    url: '/wms/bin',
    method: 'put',
    data: data
  })
}

//仓库状态修改
export function changeBinStatus(id, storeStatus) {
  const data = {
    id,
    binStatus
  }
  return request({
    url: '/wms/bin/changeStatus',
    method: 'put',
    data: data
  })
}

//删除仓库
export function delBin(id) {
  return request({
    url: '/wms/bin/' + id,
    method: 'delete'
  })
}

//导出仓库信息
export function exportBin(query) {
    return request({
        url: '/wms/bin/export',
        method: 'get',
        params: query
      })
}
