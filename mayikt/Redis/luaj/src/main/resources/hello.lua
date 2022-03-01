local luaVar =  {}
-- 无返回对象的函数
function helloSimple()
print 'hello, Java in Lua'
end

-- 有返回，无参数的函数
function luaVar.hello()
print 'hello in lua'
return 'hello, lua'
end

-- 有返回，参数为string数据
function luaVar.test(str)
print('data from java is:'..str)
return 'the parameter is '..str
end
-- 返回一个lua对象
function luaVar.getObj()
   return {
        ['userId'] = '9999', 
        ['services'] = 
            {{
                'eat',
                'drink'
            }, {
                'eat2',
                'drink2'
            }}
    }
end
--[[
   infoObj-json示例:
        {
            'userId': '1111',   
            'services': [{                
               '0' : 'eat-test',
               '1' : 'drink-test'    
            }]
      }
--]]
-- 传入一个lua对象
function luaVar.readInfo(infoObj)
   return infoObj.userId
end

return luaVar