//
//  StudentMainPageController.swift
//  seven
//
//  Created by Airing on 15/11/6.
//  Copyright © 2015年 Airing. All rights reserved.
//


import UIKit
import SwiftHTTP
import JSONJoy

class StudentMainPageController: UIViewController {
    
    var stuId:Int?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func btnSelectCourse() {
        self.performSegueWithIdentifier("SelectCourseSegue", sender: self.stuId!)
    }
    
    @IBAction func btnEditStudent() {
        self.performSegueWithIdentifier("EditStudentSegue", sender: self.stuId!)
    }
    
    @IBAction func btnShowCourse() {
        self.performSegueWithIdentifier("ShowCourseSegue", sender: self.stuId!)
    }

    @IBAction func btnShowScore() {
        self.performSegueWithIdentifier("ShowScoreSegue", sender: self.stuId!)
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        if (segue.identifier == "EditStudentSegue") {
            let controller = segue.destinationViewController as! UpdateStudentInfoController
            controller.stuId = self.stuId
        }
        if (segue.identifier == "SelectCourseSegue") {
            let controller = segue.destinationViewController as! SelectCourseController
            controller.stuId = self.stuId
        }
        if (segue.identifier == "ShowCourseSegue") {
            let controller = segue.destinationViewController as! ShowMyCourseController
            controller.stuId = self.stuId
        }
        if (segue.identifier == "ShowScoreSegue") {
            let controller = segue.destinationViewController as! ShowMyScoresController
            controller.stuId = self.stuId
        }
    }

}