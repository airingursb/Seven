//
//  ShowStudentScoreController.swift
//  seven
//
//  Created by Airing on 15/11/7.
//  Copyright © 2015年 Airing. All rights reserved.
//

import UIKit
import JSONJoy
import SwiftHTTP

class ShowStudentScoreController: UIViewController {
    
    struct ResponseData : JSONJoy {
        var result: Int?
        var studentNos:[String] = []
        var studentNames:[String] = []
        var totalScores:[Int] = []
        
        init(_ decoder: JSONDecoder) {
            result = decoder["result"].integer
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

            if let tscore = decoder["totalScores"].array {
                for addrDecoder in tscore {
                    totalScores.append(addrDecoder.integer!)
                }
            }
        }
    }
    

    @IBOutlet weak var lblShowStudentScores: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func showStudentScores() {
        var msg:String = ""
        
        let request = HTTPTask()
        request.POST("http://localhost:8080/seven/show_student_scores.action", parameters: nil, completionHandler: {(response: HTTPResponse) in
            if let res: AnyObject = response.responseObject {
                let data = ResponseData(JSONDecoder(res))
                print("result:\(data.result!)")
                if (data.result! == 1) {
                    print(data.studentNos.count)
                    for (var i = 0; i < data.studentNos.count; i++){
                        msg += "学号：\(data.studentNos[i]) \t 学生姓名：\(data.studentNames[i]) \n选修总学分：\(data.totalScores[i]) \n"
                    }
                    print(msg)
                    print("showStudentScores secceed")
                    NSOperationQueue.mainQueue().addOperationWithBlock({ () -> Void in
                        
                        self.lblShowStudentScores.text = msg
                    })
                } else {
                    print("showStudentScores fail")
                }
            }
        })
    }
}