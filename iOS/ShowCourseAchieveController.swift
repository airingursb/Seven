//
//  ShowCourseAchieveController.swift
//  seven
//
//  Created by Airing on 15/11/7.
//  Copyright © 2015年 Airing. All rights reserved.
//

import UIKit
import JSONJoy
import SwiftHTTP

class ShowCourseAchieveController: UIViewController, UITextFieldDelegate {
    
    struct ResponseData : JSONJoy {
        var result: Int?
        var courseName:[String] = []
        var studentNos:[String] = []
        var studentNames:[String] = []
        var courseAchieve:[Int] = []
        
        init(_ decoder: JSONDecoder) {
            result = decoder["result"].integer
            if let cname = decoder["courseName"].array {
                for addrDecoder in cname {
                    courseName.append(addrDecoder.string!)
                }
            }
            if let sno = decoder["studentNos"].array {
                for addrDecoder in sno {
                    studentNos.append(addrDecoder.string!)
                }
            }
            if let sname = decoder["studentNames"].array {
                for addrDecoder in sname {
                    studentNames.append(addrDecoder.string!)
                }
            }
            if let cachieve = decoder["courseAchieve"].array {
                for addrDecoder in cachieve {
                    courseAchieve.append(addrDecoder.integer!)
                }
            }
        }
    }
    
    @IBOutlet weak var txtCourseNo: UITextField!
    @IBOutlet weak var lblShowCourseAchieve: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func textFieldShouldReturn(textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func showCourseAchieve() {
        var msg:String = ""
        
        let request = HTTPTask()
        let params: Dictionary<String,AnyObject> = ["courseNo": txtCourseNo.text!]
        request.POST("http://localhost:8080/seven/show_course_achieve.action", parameters: params, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let data = ResponseData(JSONDecoder(res))
                print("result:\(data.result!)")
                if (data.result! == 1) {
                    NSOperationQueue.mainQueue().addOperationWithBlock({ () -> Void in
                        print("data.courseName.count: \(data.courseName.count)" )
                        for (var i = 0; i < data.courseName.count; i++){
                            msg += "课程名：\(data.courseName[i]) \t 学号：\(data.studentNos[i]) \n学生姓名：\(data.studentNames[i]) \t 成绩：\(data.courseAchieve[i]) \n"
                        }
                  //      print("data.courseNo[0]: \(data.courseNo[0])")
                        print(msg)
                        self.lblShowCourseAchieve.text = msg
                    })
                    
                    print("showCourseAchieve secceed")
                } else {
                    print("showCourseAchieve fail")
                }
            }
        })
        
    }
    
    
}