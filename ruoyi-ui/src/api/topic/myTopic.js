import request from '@/utils/request'

// 查询我的选题列表
export function listTopic(query) {
  return request({
    url: '/topic/mytopic/list',
    method: 'get',
    params: query
  })
}

// 查询我的选题详细
export function getTopic(id) {
  return request({
    url: '/topic/mytopic/' + id,
    method: 'get'
  })
}

// 新增我的选题
export function addTopic(data) {
  return request({
    url: '/topic/mytopic',
    method: 'post',
    data: data
  })
}

// 修改我的选题
export function updateTopic(data) {
  return request({
    url: '/topic/mytopic',
    method: 'put',
    data: data
  })
}

// 删除我的选题
export function delTopic(id) {
  return request({
    url: '/topic/mytopic/' + id,
    method: 'delete'
  })
}
