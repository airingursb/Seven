//
//  ShowMyCourseController.swift
//  seven
//
//  Created by Airing on 15/11/7.
//  Copyright © 2015年 Airing. All rights reserved.
//

import UIKit
import JSONJoy
import SwiftHTTP

class ShowMyCourseController: UIViewController {
    
    struct ResponseData : JSONJoy {
        var result: Int?
        var courseNo:[String] = []
        var courseName:[String] = []
        var teacherName:[String] = []
        var courseScore:[Int] = []

        init(_ decoder: JSONDecoder) {
            result = decoder["result"].integer
            if let cno = decoder["courseNo"].array {
                for addrDecoder in cno {
                    courseNo.append(addrDecoder.string!)
                }
            }
            if let cname = decoder["courseName"].array {
                for addrDecoder in cname {
                    courseName.append(addrDecoder.string!)
                }
            }
            if let tname = decoder["teacherName"].array {
                for addrDecoder in tname {
                    teacherName.append(addrDecoder.string!)
                }
            }
            if let cscore = decoder["courseScore"].array {
                for addrDecoder in cscore {
                    courseScore.append(addrDecoder.integer!)
                }
            }
        }
    }


    
    var stuId:Int?
    
    @IBOutlet weak var txtShowMyCourse: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func showMyCourse() {
        var msg:String = ""
        
        let request = HTTPTask()
        let params: Dictionary<String,AnyObject> = ["studentId": stuId!]
        request.POST("http://localhost:8080/seven/show_my_course.action", parameters: params, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let data = ResponseData(JSONDecoder(res))
                print("result:\(data.result!)")
                if (data.result! == 1) {
                    print(data.courseNo.count)
                    for (var i = 0; i < data.courseNo.count; i++){
                        msg += "课程号：\(data.courseNo[i]) \t 课程名：\(data.courseName[i]) \n教师姓名：\(data.teacherName[i]) \t 学分：\(data.courseScore[i]) \n"
                    }
                    print(msg)
                    print("showMyCourse secceed")
                    NSOperationQueue.mainQueue().addOperationWithBlock({ () -> Void in
                        
                       self.txtShowMyCourse.text = msg
                    })
                } else {
                    print("showMyCourse fail")
                }
            }
        })
        
        
        
    }
}
